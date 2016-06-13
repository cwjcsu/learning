/**
 * Copyright (c) 2009--2010 Red Hat, Inc.
 * <p/>
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 * <p/>
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package com.cwjcsu.common;

import com.cwjcsu.common.exec.ExecuteResult;
import com.cwjcsu.common.exec.LocalCommandService;
import org.slf4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Simple file utilities to read/write strings to a file on disk.
 *
 * @version $Rev$
 */
public class FileUtils {

    private static Logger log = _.COMMON;

    private FileUtils() {
    }

    /**
     * Save a String to a file on disk using specified path.
     *
     * WARNING: This deletes the original file before it writes.
     *
     * @param contents
     *            to save to file on disk
     * @param path
     *            to save file to.
     */
    public static void writeStringToFile(String contents, String path) {
        try {
            File file = new File(path);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Writer output = new BufferedWriter(new FileWriter(file));
            try {
                output.write(contents);
            } finally {
                output.close();
            }
        } catch (Exception e) {
            log.error("Error trying to write file to disk: [" + path + "]", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a file off disk into a String and return it.
     *
     * Expect weird stuff if the file is not textual.
     *
     * @param path
     *            of file to read in
     * @return String containing file.
     */
    public static String readStringFromFile(String path) {
        if (log.isTraceEnabled())
            log.trace("readStringFromFile: " + path);

        File f = new File(path);
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(f));
            StringWriter writer = new StringWriter();
            IOUtils.copyWriter(input, writer);
            String contents = writer.toString();
            return contents;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    public static List<String> readLinesFromFile(String path) {
        if (log.isTraceEnabled())
            log.trace("readLinesFromFile: " + path);

        File f = new File(path);
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(f));
            String line = null;
            List<String> lines = new ArrayList<String>();
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * Read a file off disk into a byte array with specified range
     *
     * This can use lots of memory if you read a large file
     *
     * @param fileToRead
     *            File to read part of into byte array
     * @param start
     *            index of read
     * @param end
     *            index of read
     * @return byte[] array from file.
     */
    public static byte[] readByteArrayFromFile(File fileToRead, long start,
                                               long end) {
        log.debug("readByteArrayFromFile: " + fileToRead.getAbsolutePath()
                + " start: " + start + " end: " + end);

        int size = (int) (end - start);
        log.debug("size of array: " + size);
        // Create the byte array to hold the data
        byte[] bytes = new byte[size];
        InputStream is = null;
        try {
            is = new FileInputStream(fileToRead);
            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            // Skip ahead
            is.skip(start);
            // start reading
            while (offset < bytes.length && (numRead) >= 0) {
                numRead = is.read(bytes, offset, bytes.length - offset);
                offset += numRead;
            }
        } catch (FileNotFoundException fnf) {
            log.error("Could not read from: " + fileToRead.getAbsolutePath());
            throw new RuntimeException(fnf);
        } catch (IOException e) {
            log.error("Could not read from: " + fileToRead.getAbsolutePath());
            throw new RuntimeException(e);

        } finally {
            IOUtils.closeQuietly(is);
        }
        return bytes;
    }

    /**
     * check file can read\write\execute or their combinations.
     *
     * @param file
     * @param mode
     *            string of combinations of "r","w", "e" respectively for
     *            read,write,execute.
     * @return
     */
    public static boolean checkFile(String file, String mode) {
        File f = new File(file);
        if (!f.exists()) {
            return false;
        }
        if (mode.contains("r") && !f.canRead())
            return false;
        if (mode.contains("w") && !f.canWrite()) {
            return false;
        }
        if (mode.contains("e") && !f.canExecute()) {
            return false;
        }
        return true;
    }

    public static void makeRsyncPath(String path) {
        if (path == null || "".equals(path.trim()))
            return;
        File f = new File(path);
        if (!path.endsWith("/")) {
            f = f.getParentFile();
        }
        if (f != null && !f.exists()) {
            f.mkdirs();
        }
    }

    public static void makeDirForFile(File file) throws IOException {
        File pf = file.getParentFile();
        if (!pf.exists()) {
            pf.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void main(String[] args) {
        File f = new File("/tmp/ha/b2/c3/d2.txt");
        try {
            makeDirForFile(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(f.exists());
        f.delete();
    }

    /**
     *
     * @param file
     * @return
     */
    public static FileStat fstat(File file) {
        List<FileStat> stats = fstat(Arrays.asList(file));
        return stats.size() > 0 ? stats.get(0) : null;
    }

    public static boolean isExecutable(String file) {
        File f = new File(file);
        return f.canExecute();
    }

    /**
     *
     * @param files
     *            exists files
     * @return empty list if the command not exists
     */
    public static List<FileStat> fstat(List<File> files) {
        List<FileStat> stats = new ArrayList<FileStat>(files.size());
        String cmd = HaEnv.getFstatCommand();
        if (!isExecutable(cmd)) {
            // throw new RuntimeException("command " + cmd +
            // " not executable.");
            log.warn("command '{}' not executable", cmd);
            return Collections.EMPTY_LIST;
        }
        StringBuilder sb = new StringBuilder(cmd);
        for (File f : files) {
            sb.append(" ");
            sb.append(f.getAbsolutePath());
        }
        ExecuteResult er = null;
        try {
            er = LocalCommandService.getInstance().executeCommand(sb.toString().split("\\s+"), 5000);
            if (er.getExitCode() != 0) {
                throw new RuntimeException(er.getErrorOut());
            }
            String out = er.getExecuteOut();
            String[] results = out.split("\\r?\\n");
            for (String result : results) {
                result = result.trim();
                if (result.startsWith("("))
                    result = result.substring(1);
                if (result.endsWith(")"))
                    result = result.substring(0, result.length() - 1);
                String[] infos = result.split(",");
                if (infos.length != 6) {
                    throw new RuntimeException("cant not parse line " + result);
                }
                for (int i = 0; i < infos.length; i++) {
                    infos[i] = StringUtils.trim(infos[i].trim(), "'");
                    infos[i] = infos[i].trim();
                }
                FileStat fs = new FileStat(new File(infos[0]),
                        Integer.parseInt(infos[1]), Integer.parseInt(infos[2]),
                        infos[3], Integer.parseInt(infos[4]), infos[5]);
                stats.add(fs);
            }
            return stats;
        } finally {
            if (er != null) {

            }
        }
    }

    /**
     * 更改文件的ownership，
     *
     * @param file
     * @param user
     * @param group
     * @return chown的退出码, if 0,success,else fail
     * @throws NullPointerException
     *             if both user 和group is null
     */
    public static int chown(File file, String user, String group) {
        String path = file.getPath();
        if (user == null && group == null) {
            throw new NullPointerException("user and group both null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("chown -R ");
        if (user != null) {
            sb.append(user);
        }
        if (group != null) {
            sb.append(":");
            sb.append(group);
        }
        sb.append(" ");
        sb.append(path);
        ExecuteResult er = null;
        String command = sb.toString();
        try {
            er = LocalCommandService.getInstance().executeCommand(command.split("\\s+"), 5000);
            if (er.getExitCode() != 0) {
                log.warn("execute command '{}' error:{}", command,
                        er.getErrorOut());
            }
            return er.getExitCode();
        } finally {
        }
    }

    public static boolean chmod(File file, String octMode, String user,
                                String group) {
        if (user != null || group != null) {
            chown(file, user, group);
        }
        StringBuilder sb = new StringBuilder("chmod -R ");
        sb.append(octMode);
        sb.append(" ");
        sb.append(file.getPath());
        ExecuteResult er = null;
        String command = sb.toString();
        try {
            er = LocalCommandService.getInstance().executeCommand(command.split("\\s+"), 6000, true);
            if (er.getExitCode() != 0) {
                log.warn("execute command '{}' error:{}", command,
                        er.getErrorOut());
                return false;
            }
            return true;
        } finally {
        }
    }

    public static boolean chmod(File file, String octMode) {
        return chmod(file, octMode, null, null);
    }

    public static boolean remove(String path) {
        File f = new File(path);
        return f.delete();
    }

    /**
     * check whether file1 and file2 are exactly same file
     *
     * @param file1
     * @param file2
     * @return
     * @throws IOException
     */
    public static boolean eq(File file1, File file2) {
        try {
            return file1.getCanonicalPath().equals(file2.getCanonicalPath());
        } catch (IOException e) {
            log.error("eq file {} and {} fail", file1, file2, e);
            return false;
        }
    }

    /**
     * check if file is a valid file name in Linux.
     * @param file
     * @return
     */
    public static boolean isValidFilename(String file) {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

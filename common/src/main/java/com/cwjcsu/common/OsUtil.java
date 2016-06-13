package com.cwjcsu.common;

import ch.qos.logback.core.util.FileUtil;
import com.cwjcsu.common.exec.ExecuteResult;
import com.cwjcsu.common.exec.LocalCommandService;
import com.cwjcsu.common.pojo.NetworkInfo;
import com.cwjcsu.common.pojo.OsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author atlas
 * @date 2012-8-24
 */
public final class OsUtil {
    private final static Logger logger = LoggerFactory.getLogger(OsUtil.class);

    private OsUtil() {

    }

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static String ARCH = System.getProperty("os.arch").toLowerCase();

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
                .indexOf("aix") > 0);

    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }



    public static boolean isWinHighThanXp() {
        if (isWindows()) {
            // VISTA以上版本号为6以上
            double osVersion = Double.valueOf(System.getProperty("os.version"));
            return osVersion >= 6;
        }
        return false;
    }

    public static boolean isArch32() {
        return !isArch64();
    }

    public static boolean isArch64() {
        return ARCH.indexOf("64") >= 0;
    }

    /**
     * macosx,solaris,linux,windows
     *
     * @return
     */
    public static String getOsType() {
        if (isMac()) {
            return "macosx";
        } else if (isSolaris()) {
            return "solaris";
        } else if (isUnix()) {
            return "linux";
        }
        return "windows";
    }


    /**
     * 获取默认系统名称"os.name-os.version-os.arch"
     *
     * @return
     */
    public static OsInfo getOsInfo() {
        String name = System.getProperty("os.name");
        String version = System.getProperty("os.version");
        String arch = System.getProperty("os.arch");
        OsInfo info = new OsInfo();
        info.setVersion(version);
        info.setArch(arch);
        info.setName(name);
        return info;
    }

    /**
     * 获取所有的网络接口
     *
     * @return
     */
    public static List<NetworkInfo> getNetwork() {
        Enumeration<NetworkInterface> nets;
        List<NetworkInfo> vos = new ArrayList<NetworkInfo>();
        try {
            nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netIf : Collections.list(nets)) {
                if (netIf.isLoopback() || netIf.isVirtual() || isVirtual(netIf)) {
                    // || netIf.isPointToPoint() || !netIf.isUp()) {
                    continue;
                }

//				Enumeration<InetAddress> addrs = netIf.getInetAddresses();
                List<InterfaceAddress> addrs = netIf.getInterfaceAddresses();
                if (addrs.size() > 0) {
                    for (InterfaceAddress address : addrs) {
                        if (address == null || address.getAddress() == null) {
                            continue;
                        }
                        if (address.getAddress() instanceof Inet6Address) {
                            continue;
                        }
                        NetworkInfo vo = new NetworkInfo();
                        vo.setNetIfName(netIf.getName());
                        byte[] macAddrBytes = netIf.getHardwareAddress();
                        if (macAddrBytes != null) {
                            String macAddr = StringUtils.bytesToHex(macAddrBytes).toUpperCase();
                            vo.setMac(formatMacAddress(macAddr));
                        }
                        String ip = address.getAddress().getHostAddress();
                        if (StringUtils.isEmpty(ip)) {
                            continue;
                        }
                        vo.setIp(ip);
                        if (address.getBroadcast() != null) {
                            vo.setBroadcastIp(address.getBroadcast().getHostAddress());
                        }
                        vo.setNetworkPrefixLength(address.getNetworkPrefixLength());
                        //获取hostname可能导致阻塞
//						vo.setHostname(address.getHostName());
                        vos.add(vo);
                    }
                } else {
                    NetworkInfo vo = new NetworkInfo();
                    vo.setNetIfName(netIf.getName());
                    byte[] macAddrBytes = netIf.getHardwareAddress();
                    if (macAddrBytes != null && macAddrBytes.length > 0) {
                        String macAddr = StringUtils.bytesToHex(macAddrBytes).toUpperCase();
                        if (!StringUtils.isEmpty(macAddr)) {
                            vo.setMac(formatMacAddress(macAddr));
                            vos.add(vo);
                        }
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("get network information error. ", e);
        }
        for (NetworkInfo info : vos) {
            logger.debug("NetworkInfo : " + info);
        }
        return vos;
    }

    /**
     * @param netIf
     * @return
     */
    private static boolean isVirtual(NetworkInterface netIf) {
        String displayName = netIf.getDisplayName();
        if (!StringUtils.isEmpty(displayName)) {
            displayName = displayName.toLowerCase();
            if (displayName.contains("virtualbox") || displayName.contains("vmware") || displayName.contains("vnic")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 格式化mac地址，如果mac地址为空则返回0000..
     *
     * @param macAddr
     * @return
     */
    private static String formatMacAddress(String macAddr) {
        if (StringUtils.isEmpty(macAddr)) {
            return "000000000000";
        }
        return macAddr;
    }

    public static String getNetworkIpByMac(String macAddress) {
        List<NetworkInfo> infos = getNetwork();
        for (NetworkInfo info : infos) {
            if (macAddress.equalsIgnoreCase(info.getMac())) {
                return info.getIp();
            }
        }
        return null;
    }

    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(OsUtil.getOsInfo());
        System.out.println(OsUtil.getProcessId());
    }

    public static List<String> getWindowsAllHdAddresses() {
        List<String> allHdAddresses = new ArrayList<String>();
        ExecuteResult executeCommand = LocalCommandService.getInstance().executeCommand(new String[]{"cmd.exe", "/c", "chcp 437 & ipconfig /all"}, 10000L);
        if (executeCommand.getExitCode() == 0) {
            String output = executeCommand.getExecuteOut();
            logger.debug(output);
            Pattern pattern = Pattern.compile(".*Physical Address.*: (.*)");
            Matcher matcher = pattern.matcher(output);
            while (matcher.find()) {
                allHdAddresses.add(matcher.group(1).replaceAll(":", "").replaceAll("-", "").toUpperCase());
            }
        }
        return allHdAddresses;
    }

    public static List<String> getSolarisAllHdAddresses() {
        List<String> allHdAddresses = new ArrayList<String>();
        ExecuteResult executeCommand = LocalCommandService.getInstance().executeCommand(new String[]{"/usr/bin/bash", "-c", "arp -a"}, 10000L);
        if (executeCommand.getExitCode() == 0) {
            String output = executeCommand.getExecuteOut();
            logger.debug(output);
            Pattern pattern = Pattern.compile(".*([0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2})");
            Matcher matcher = pattern.matcher(output);
            while (matcher.find()) {
                allHdAddresses.add(matcher.group(1).replaceAll(":", "").replaceAll("-", "").toUpperCase());
            }
        }
        return allHdAddresses;
    }

    public static String getHostName() {
        try {
            String result = InetAddress.getLocalHost().getHostName();
            if (StringUtils.notEmpty(result))
                return result;
        } catch (UnknownHostException e) {
            // failed;  try alternate means.
        }

        // try environment properties.
        String host = System.getenv("COMPUTERNAME");
        if (host != null)
            return host;
        host = System.getenv("HOSTNAME");
        if (host != null)
            return host;

        // undetermined.
        return null;
    }

    private static Long processId;

    /**
     * @return the current jvm process id or null if failed.
     */
    public static Long getProcessId() {
        if (processId != null) {
            return processId;
        }
        synchronized (OsUtil.class) {
            if (processId != null) {
                return processId;
            }
            try {
                final String jvmName = ManagementFactory.getRuntimeMXBean()
                        .getName();
                final int index = jvmName.indexOf('@');
                if (index > 1) {
                    try {
                        return (processId = Long.parseLong(jvmName.substring(0,
                                index)));
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }
                String pidFile = System.getProperty("ydw.pid");
                if (pidFile != null) {
                    File file = (pidFile != null) ? new File(pidFile) : null;
                    if (file != null && file.canRead()) {
                        String pid = FileUtils.readStringFromFile(pidFile);
                        return (processId = Long.parseLong(pid));
                    }
                }
            } catch (Exception e) {
                System.err.println("getProcessId error");
                e.printStackTrace(System.err);
            }
            return null;
        }
    }

}

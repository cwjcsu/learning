/*$$
 * author                          date   comment
 * cwjcsu@gmail.com       2014/8/20  Created
 */
package com.cwjcsu.common.template;

/**
 * @author atlas
 */
public class Part {
   private String part;
    private boolean variable;

    public Part(String part, boolean variable) {
        this.part = part;
        this.variable = variable;
    }

    public String getPart() {
        return part;
    }

    public boolean isVariable() {
        return variable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Part)) return false;

        Part part1 = (Part) o;

        if (variable != part1.variable) return false;
        if (part != null ? !part.equals(part1.part) : part1.part != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = part != null ? part.hashCode() : 0;
        result = 31 * result + (variable ? 1 : 0);
        return result;
    }
}

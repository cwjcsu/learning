/*$$
 * author                          date   comment
 * cwjcsu@gmail.com       2014/8/20  Created
 */
package com.cwjcsu.common.template;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atlas
 */
public class Parts {
    private String template = null;
    private List<Part> parts = new ArrayList<Part>();
    private int varCount = 0;

    public Parts(String template) {
        this.template = template;
    }

    public void addPart(Part part) {
        if (parts.size() > 0 && part.isVariable()) {
            if (parts.get(parts.size() - 1).isVariable()) {
                throw new IllegalArgumentException("Invalid template,2 variables are connected");
            }
        }
        this.parts.add(part);
        varCount += (part.isVariable() ? 1 : 0);
    }

    public boolean isTemplate() {
        return varCount > 0;
    }

    public List<Part> getParts() {
        return parts;
    }

    public String getTemplate() {
        return template;
    }
}

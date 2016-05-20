package team.xuli.toe.domain;

/**
 * @author: 徐清锋
 * 创建时间：2016/5/20
 * 创建原因：
 */
public class ItemType {
    private int itemTypeId;
    private String name;
    private String description;
    private boolean status;

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

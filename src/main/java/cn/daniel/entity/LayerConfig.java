package cn.daniel.entity;

import java.io.Serializable;

public class LayerConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String owner;

    private String tablename;

    private String field_id;

    private String field_updatetime;

    private String field_x;

    private String field_y;

    private String field_label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getField_id() {
        return field_id;
    }

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    public String getField_updatetime() {
        return field_updatetime;
    }

    public void setField_updatetime(String field_updatetime) {
        this.field_updatetime = field_updatetime;
    }

    public String getField_x() {
        return field_x;
    }

    public void setField_x(String field_x) {
        this.field_x = field_x;
    }

    public String getField_y() {
        return field_y;
    }

    public void setField_y(String field_y) {
        this.field_y = field_y;
    }

    public String getField_label() {
        return field_label;
    }

    public void setField_label(String field_label) {
        this.field_label = field_label;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((field_id == null) ? 0 : field_id.hashCode());
        result = prime * result + ((field_label == null) ? 0 : field_label.hashCode());
        result = prime * result + ((field_updatetime == null) ? 0 : field_updatetime.hashCode());
        result = prime * result + ((field_x == null) ? 0 : field_x.hashCode());
        result = prime * result + ((field_y == null) ? 0 : field_y.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((tablename == null) ? 0 : tablename.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LayerConfig other = (LayerConfig) obj;
        if (field_id == null) {
            if (other.field_id != null)
                return false;
        } else if (!field_id.equals(other.field_id))
            return false;
        if (field_label == null) {
            if (other.field_label != null)
                return false;
        } else if (!field_label.equals(other.field_label))
            return false;
        if (field_updatetime == null) {
            if (other.field_updatetime != null)
                return false;
        } else if (!field_updatetime.equals(other.field_updatetime))
            return false;
        if (field_x == null) {
            if (other.field_x != null)
                return false;
        } else if (!field_x.equals(other.field_x))
            return false;
        if (field_y == null) {
            if (other.field_y != null)
                return false;
        } else if (!field_y.equals(other.field_y))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (tablename == null) {
            if (other.tablename != null)
                return false;
        } else if (!tablename.equals(other.tablename))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LayerConfig [id=" + id + ", owner=" + owner + ", tablename=" + tablename + ", field_id=" + field_id
                + ", field_updatetime=" + field_updatetime + ", field_x=" + field_x + ", field_y=" + field_y
                + ", field_label=" + field_label + "]";
    }
}

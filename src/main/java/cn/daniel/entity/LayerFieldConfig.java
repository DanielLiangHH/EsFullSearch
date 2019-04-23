package cn.daniel.entity;

import java.io.Serializable;

public class LayerFieldConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String owner;

    private String tablename;

    private String fieldName;

    private String akaName;

    private String isShow;

    private String isSearch;

    private String decode;

    private String showIndex;

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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getAkaName() {
        return akaName;
    }

    public void setAkaName(String akaName) {
        this.akaName = akaName;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

    public String getDecode() {
        return decode;
    }

    public void setDecode(String decode) {
        this.decode = decode;
    }

    public String getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(String showIndex) {
        this.showIndex = showIndex;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((akaName == null) ? 0 : akaName.hashCode());
        result = prime * result + ((decode == null) ? 0 : decode.hashCode());
        result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isSearch == null) ? 0 : isSearch.hashCode());
        result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((showIndex == null) ? 0 : showIndex.hashCode());
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
        LayerFieldConfig other = (LayerFieldConfig) obj;
        if (akaName == null) {
            if (other.akaName != null)
                return false;
        } else if (!akaName.equals(other.akaName))
            return false;
        if (decode == null) {
            if (other.decode != null)
                return false;
        } else if (!decode.equals(other.decode))
            return false;
        if (fieldName == null) {
            if (other.fieldName != null)
                return false;
        } else if (!fieldName.equals(other.fieldName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isSearch == null) {
            if (other.isSearch != null)
                return false;
        } else if (!isSearch.equals(other.isSearch))
            return false;
        if (isShow == null) {
            if (other.isShow != null)
                return false;
        } else if (!isShow.equals(other.isShow))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (showIndex == null) {
            if (other.showIndex != null)
                return false;
        } else if (!showIndex.equals(other.showIndex))
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
        return "LayerConfig [id=" + id + ", owner=" + owner + ", tablename=" + tablename + ", fieldName=" + fieldName
                + ", akaName=" + akaName + ", isShow=" + isShow + ", isSearch=" + isSearch + ", decode=" + decode
                + ", showIndex=" + showIndex + "]";
    }
}

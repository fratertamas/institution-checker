package hu.frt.model;

import java.util.List;

public class CodeOfTaxNumberArea {
    private String dictname;
    private List<TaxNumberArea> rows;

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname;
    }

    public List<TaxNumberArea> getRows() {
        return rows;
    }

    public void setRows(List<TaxNumberArea> rows) {
        this.rows = rows;
    }
}

package com.codigoartesanal.lupa.model.dto;

/**
 * Created by betuzo on 7/03/16.
 */
public class GraficaDTO {

    private String label;
    private Double y;
    private Long count;

    public GraficaDTO(String label, Double y, Long count) {
        this.label = label;
        this.y = y;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

package tim.lab7.rest.model;


import java.util.List;

public class StationPOJO {
    private String stationId;
    private Address stationAddress;
    private List<SubstancePOJO> substances;

    public StationPOJO() {
    }

    public StationPOJO(String stationId, Address stationAddress, List<SubstancePOJO> substances) {
        this.stationId = stationId;
        this.stationAddress = stationAddress;
        this.substances = substances;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Address getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(Address stationAddress) {
        this.stationAddress = stationAddress;
    }

    public List<SubstancePOJO> getSubstances() {
        return substances;
    }

    public void setSubstances(List<SubstancePOJO> substances) {
        this.substances = substances;
    }
}
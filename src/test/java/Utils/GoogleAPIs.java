package Utils;

public enum GoogleAPIs {
    fitnessDataSources("/users/me/dataSources");

    private String apiName;
    GoogleAPIs(String apiName) {
        this.apiName = apiName;
    }

    public String getSourceURL() {
        return this.apiName;
    }
}

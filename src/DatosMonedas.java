public class DatosMonedas {
    private String timeLastUpdateUtc;
    private String baseCode;
    private String targetCode;
    private double conversionResult;

    // Getters y setters
    public String getTimeLastUpdateUtc() {
        return timeLastUpdateUtc;
    }

    public void setTimeLastUpdateUtc(String timeLastUpdateUtc) {
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }

    @Override
    public String toString() {
        return
                "Tiempo de Cambio: " + timeLastUpdateUtc + '\n' +
                "Moneda base: " + baseCode + '\n' +
                "Moneda de cambio: " + targetCode + '\n' +
                "Resultado: " + conversionResult + targetCode;
    }
}

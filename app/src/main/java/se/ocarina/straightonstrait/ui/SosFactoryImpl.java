package se.ocarina.straightonstrait.ui;

class SosFactoryImpl implements SosFactory {
    @Override
    public Transportation getTransportation(String text) {
        switch (text) {
            case "BUS":
            case "EXB":
            case "NB":
            case "TB":
                return Transportation.Bus;
            case "F":
                return Transportation.Ferry;
            case "IC":
            case "LYN":
            case "S":
            case "REG":
            case "TOG":
                return Transportation.Rail;
            case "M":
                return Transportation.Subway;
            case "WALK":
                return Transportation.Walk;
            default:
                throw new IllegalArgumentException(text);
        }
    }
}

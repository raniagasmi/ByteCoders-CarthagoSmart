package ByteCoders.ViewController;

import java.time.DayOfWeek;

public class CollectPoint {
    private String name;
    private TypeDechet typeDechet;
    private DayOfWeek collectDay;

    public CollectPoint(String name,  TypeDechet typeDechet, DayOfWeek collectDay) {
        this.name = name;
        this.typeDechet = typeDechet;
        this.collectDay = collectDay;
    }

    public TypeDechet getTypeDechets() {
        return typeDechet;
    }

    public DayOfWeek getCollectDay()
    {
        return collectDay;
    }
}

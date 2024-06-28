package org.example.task4;

public class Record {
    String name;
    int performanceScore;

    public Record(String name, int performanceScore) {
        this.name = name;
        this.performanceScore = performanceScore;
    }

    public String getName() {
        return name;
    }

    public int getPerformanceScore() {
        return performanceScore;
    }
}

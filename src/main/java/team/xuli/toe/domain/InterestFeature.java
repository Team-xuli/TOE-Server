package team.xuli.toe.domain;

/**
 * @author: 徐清锋
 * 创建时间：2016/6/4
 * 创建原因：
 */
public class InterestFeature {
    private int interestFeatureId;
    private int interestMainId;
    private String name;
    private double weight;

    public int getInterestFeatureId() {
        return interestFeatureId;
    }

    public void setInterestFeatureId(int interestFeatureId) {
        this.interestFeatureId = interestFeatureId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterestMainId() {
        return interestMainId;
    }

    public void setInterestMainId(int interestMainId) {
        this.interestMainId = interestMainId;
    }
}

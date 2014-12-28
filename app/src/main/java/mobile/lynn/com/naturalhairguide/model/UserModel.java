package mobile.lynn.com.naturalhairguide.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;
import java.util.Calendar;

@Table(name = "User")
public class UserModel extends Model {

    @Column(name = "email", notNull = true)
    private String email;

    @Column(name = "hairType")
    private HairType hairType;

    @Column(name = "checkupFrequency")
    private Frequency checkupFrequency;

    @Column(name = "lastHairWash")
    private Date lastHairWash;

    @Column(name = "lastDeepCondition")
    private Date lastDeepCondition;

    @Column(name = "lastHotOilTreatment")
    private Date lastHotOilTreatment;

    @Column(name = "nextCheckup")
    private Date nextCheckup;

    @Column(name = "hairLength")
    private HairLength hairLength;

    @Column(name = "units")
    private Units units;

    public UserModel() {
        this(null);
    }

    public UserModel(String email) {
        super();
        this.email = email;
        this.hairType = HairType.NONE;
        this.checkupFrequency = Frequency.MONTHLY;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1970 );
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        long time = cal.getTime().getTime();

        this.lastHairWash = new Date(time);
        this.lastDeepCondition = new Date(time);
        this.lastHotOilTreatment = new Date(time);
        this.nextCheckup = new Date(Calendar.getInstance().getTime().getTime());

        this.hairLength = HairLength.NONE;
        this.units = Units.ENGLISH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HairType getHairType() {
        return hairType;
    }

    public void setHairType(HairType hairType) {
        this.hairType = hairType;
    }

    public Frequency getCheckupFrequency() {
        return checkupFrequency;
    }

    public void setCheckupFrequency(Frequency checkupFrequency) {
        this.checkupFrequency = checkupFrequency;
    }

    public Date getLastHairWash() {
        return lastHairWash;
    }

    public void setLastHairWash(Date lastHairWash) {
        this.lastHairWash = lastHairWash;
    }

    public Date getLastDeepCondition() {
        return lastDeepCondition;
    }

    public void setLastDeepCondition(Date lastDeepCondition) {
        this.lastDeepCondition = lastDeepCondition;
    }

    public Date getLastHotOilTreatment() {
        return lastHotOilTreatment;
    }

    public void setLastHotOilTreatment(Date lastHotOilTreatment) {
        this.lastHotOilTreatment = lastHotOilTreatment;
    }

    public Date getNextCheckup() {
        return nextCheckup;
    }

    public void setNextCheckup(Date nextCheckup) {
        this.nextCheckup = nextCheckup;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public HairLength getHairLength() {
        return hairLength;
    }

    public void setHairLength(HairLength hairLength) {
        this.hairLength = hairLength;
    }
}

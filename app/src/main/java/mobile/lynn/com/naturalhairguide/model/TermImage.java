package mobile.lynn.com.naturalhairguide.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "TermImage")
public class TermImage extends Model {
    @Column(name = "location")
    private int location;

    @Column(name = "term", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    private Term term;

    public TermImage() { super(); }

    public TermImage(int imageName, Term term) {
        super();
        this.location = imageName;
        this.term = term;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}

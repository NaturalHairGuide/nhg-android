package mobile.lynn.com.naturalhairguide.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Term")
public class Term extends Model implements Comparable<Term> {

    @Column(name = "term", onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private String term;

    @Column(name = "definition", onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private String definition;

    @Column(name = "mainImage")
    private int mainImage;

    public Term () {
        super();
    }

    public Term(String term, String definition, int mainImage) {
        super();
        this.term = term;
        this.definition = definition;
        this.mainImage = mainImage;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<TermImage> images() {
        return getMany(TermImage.class, "Term");
    }

    @Override
    public int compareTo(Term term) {
        int comparison = this.getTerm().compareTo(term.getTerm());
        if (comparison != 0) return comparison;

        return 0;
    }

    public int getMainImage() {
        return mainImage;
    }

    public void setMainImage(int mainImage) {
        this.mainImage = mainImage;
    }
}

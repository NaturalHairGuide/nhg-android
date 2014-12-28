package mobile.lynn.com.naturalhairguide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionTree implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name;
    public String title;
    public List<QuestionTree> options;

    public QuestionTree(String title, String name, QuestionTree[] optionList) {
        this.title = title;
        this.name = name;

        this.options = new ArrayList<QuestionTree>();
        if(optionList != null) {
            for(QuestionTree q : optionList)
                this.options.add(q);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionTree> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionTree> options) {
        this.options = options;
    }
}

package mobile.lynn.com.naturalhairguide.activity;

import android.os.Bundle;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.fragment.QuestionFragment;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.QuestionTree;
import mobile.lynn.com.naturalhairguide.model.Questions;

public class QuestionnaireActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_questionnaire);

        String questionnaireType = getIntent().getExtras().getString(Tags.QUESTIONNAIRE_TYPE);
        Questions questions = new Questions();
        Bundle args = new Bundle();

        if (questionnaireType.equals("type")) {
            QuestionTree hairTypeQuestion = questions.getHairTypeQuestionTree();
            args.putSerializable(Tags.QUESTION, hairTypeQuestion);
            args.putString(Tags.QUESTIONNAIRE_TYPE, "type");
        } else if (questionnaireType.equals("checkup")) {
            QuestionTree hairCheckupQuestion = questions.getHairCheckupQuestionTree();
            args.putSerializable(Tags.QUESTION, hairCheckupQuestion);
            args.putString(Tags.QUESTIONNAIRE_TYPE, "checkup");
        }

        QuestionFragment firstFragment = new QuestionFragment();
        firstFragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.question_frame, firstFragment)
                .commit();
    }
}

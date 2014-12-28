package mobile.lynn.com.naturalhairguide.service;

import com.activeandroid.query.Select;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.model.Term;
import mobile.lynn.com.naturalhairguide.model.TermImage;

public class DictionaryService {
    public static void initialize() {
        //Hair pH
//        addTerm("Co-wash", "this is the definition", R.drawable.term_icon);
//        addTerm("Protein Treatment", "this is the definition", R.drawable.term_icon);
//        addTerm("Shay Butter", "this is the definition", R.drawable.term_icon);
//        addTerm("Cleansing Conditioner", "this is the definition", R.drawable.term_icon);
//        addTerm("Castor Oil", "this is the definition", R.drawable.term_icon);
//        addTerm("Extra Virgin Oil", "this is the definition", R.drawable.term_icon);
//        addTerm("Jojoba Oil", "this is the definition", R.drawable.term_icon);
//        addTerm("Almond Oil", "this is the definition", R.drawable.term_icon);
//        addTerm("Coconut Oil", "this is the definition", R.drawable.term_icon);
//        addTerm("Three Strand Twist", "this is the definition", R.drawable.term_icon);
//        addTerm("Two Strand Twist", "this is the definition", R.drawable.term_icon);
//        addTerm("Bantu Knot Out", "this is the definition", R.drawable.term_icon);
//        addTerm("Finger Coils", "this is the definition", R.drawable.term_icon);
//        addTerm("Conditioner", "this is the definition", R.drawable.term_icon);
//        addTerm("Deep Condition", "this is the definition", R.drawable.term_icon);
//        addTerm("Shingling", "this is the definition", R.drawable.term_icon);

        initHairTypes();
    }

    private static void initHairTypes() {
        addTerm("2A Hair Type",
                "Fine, thin and very easy to handle; easily straightened or curled",
                R.drawable.two_a);

        addTerm("2B Hair Type",
                "Medium-textured and a little resistant to styling; has a tendency to frizz",
                R.drawable.two_b);

        addTerm("2C Hair Type",
                "Thick and coarse and more resistant to styling and will frizz easily",
                R.drawable.two_c);

        addTerm("3A Hair Type",
                "Curls are naturally big, loose and usually very shiny. Circumference: sidewalk-chalk size",
                R.drawable.three_a);

        addTerm("3B Hair Type",
                "Medium amount of curl, from bouncy ringlets to tight corkscrews. Circumference: Sharpie size",
                R.drawable.three_b);

        addTerm("3C Hair Type",
                "Subtype 3c is really more than a subtype. It's a type NaturallyCurly members developed because the original system left out this hair type, which falls between 3b and 4a, having its own special characteristics.",
                R.drawable.three_c);

        addTerm("4A Hair Type",
                "Type 4a is tightly coiled hair that has an \"S\" pattern. It has more moisture than " +
                        "4b; it has a definite curl pattern. The circumference of the spirals is close " +
                        "to that of a crochet needle. The hair can be wiry or fine-textured. It is very " +
                        "fragile with lots of strands densely packed together. Type 4 hair has fewer " +
                        "cuticle layers than other hair types, which means it has less natural protection from damage.",
                R.drawable.four_a);

        addTerm("4B Hair Type",
                "Type 4b has a \"Z\" pattern, less of a defined curl pattern. Instead of curling or " +
                        "coiling, the hair bends in sharp angles like the letter \"Z\". Type 4 hair " +
                        "has a cotton-like feel. The hair is very wiry, very tightly coiled or bent " +
                        "and very, very fragile; you must take great care when working with it. " +
                        "Type 4 hair can range from fine/thin to wiry/coarse with lots and lots of " +
                        "strands densely packed together. Type 4b hair often shrinks up to 75% of " +
                        "the actual hair length.",
                R.drawable.four_b);

        addTerm("4C Hair Type",
                "Type 4c hair is composed of curl patterns that will almost never clump without " +
                        "doing a specific hair style. It can range from fine/thin/super soft to " +
                        "wiry/coarse with lots of densely packed strands. 4c hair has been " +
                        "described as a more \"challenging\" version of 4b hair. Some say 4c looks " +
                        "identical to 4b except that the curls are so tightly kinked, there is " +
                        "seemingly no definition. 4c hair can shrink more than 75%.",
                R.drawable.four_c);
    }

    private static void addTerm(String termName, String definition, int imageName) {
        Term term;

        if (new Select().from(Term.class).where("term = '" + termName + "'").exists()) {
            term = new Select().from(Term.class).where("term = '" + termName + "'").executeSingle();
            term.setDefinition(definition);
            term.setMainImage(imageName);
            term.save();
        } else {
            term = new Term(termName, definition, imageName);
            term.save();
        }

        if (new Select().from(TermImage.class).where("term = " + term.getId()).exists()) {
            TermImage oldImage = new Select().from(TermImage.class).where("location = " + imageName).executeSingle();
            if(oldImage != null) oldImage.delete();
        }

        TermImage image = new TermImage(imageName, term);
        image.save();
    }
}

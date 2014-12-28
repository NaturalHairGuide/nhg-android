package mobile.lynn.com.naturalhairguide.model;

public class Questions {
    private QuestionTree hairTypeQuestionTree;
    private QuestionTree hairCheckupQuestionTree;

    public Questions () {
        setHairTypeQuestions();
        setHairCheckupQuestions();
    }

    private void setHairCheckupQuestions() {
        QuestionTree hotOilTreatment = new QuestionTree("Answer", "option that points to answer", null); // list of options

        QuestionTree answer1 = new QuestionTree("You are in need of something to prevent breakage and split ends. " +
                "A protein treatment that includes ingredients such as honey or banana", "Yes", null);


        QuestionTree healthyHair = new QuestionTree("Your hair seems healthy!", "No", null);
        QuestionTree dryScalp = new QuestionTree("Tea Tree Oil", "Yes", null);

        QuestionTree answer4 = new QuestionTree("Your hair may be a bit fragile. A Banana Treatment may be good.", "Yes", null);
        QuestionTree answer5 = new QuestionTree("Use avocado to help with frizziness.", "No", null);

        QuestionTree question13 = new QuestionTree("Is your diet healthy?", "No", null);

        QuestionTree question12 = new QuestionTree("Have you been using any chemicals in your hair? (e.g. hair color)", "No",
                new QuestionTree[] { answer4, question13 });

        QuestionTree question5 = new QuestionTree("Have you been using a lot of heat on your hair? (e.g. straightener, hair dryer, etc)", "Yes",
                new QuestionTree[] { answer1, question12 });

        QuestionTree question4 = new QuestionTree("Have you been in the sun a lot?", "No", null);
        QuestionTree question2 = new QuestionTree("Does your hair feel dry?", "Yes",
                new QuestionTree[] { question5 });


//        QuestionTree question8 = new QuestionTree("Are your curls bouncy or do your curls look less curly?", "No",
//                new QuestionTree[] { q12, q15 });

//        QuestionTree question10 = new QuestionTree("Is your hair frizzy?", "No",
//                new QuestionTree[] { answer5, question8 });

//        QuestionTree question1 = new QuestionTree("Are you having any breakage?", "Yes",
//                new QuestionTree[] { q14, question10 });

        QuestionTree elasticity = new QuestionTree("Elasticity", "No", null);

        QuestionTree productBuildup = new QuestionTree("It looks like you have product build up. " +
                "Protein treatments with baking soda or papaya would help.", "Yes", null);
        QuestionTree genericDry = new QuestionTree("Try the following to help moisturize your hair: Cleanse with moisturizing shampoos " +
                "or ones that are Sulfate free.\nAlways follow up with a conditioner that moisturizes well.\n" +
                "Use a water based leave-in conditioner after your wash and conditioning routine, and seal it in with a butter or oil while damp or wet.\n" +
                "Use deep conditioners weekly. You may need to apply indirect heat through something like a shower cap.\n" +
                "Evaluate if your water is hard or soft and if it needs to be treated.\n" +
                "Determine the pH of the products you use in your hair and adjust the ratios/products accordingly.\n" +
                "Drink more water.", "No", null);

        QuestionTree q13 = new QuestionTree("Do you have dandruff-like particles appearing in your hair and scalp?", "Yes",
                new QuestionTree[] { productBuildup, genericDry });
        QuestionTree q12 = new QuestionTree("Does your hair seem less shiny?", "Yes",
                new QuestionTree[] { q13, genericDry });

        QuestionTree satinPillowcase = new QuestionTree("Cotton pillow cases rob hair of its moisture. Try using a " +
                "satin pillowcase or covering your hair in a satin bonnet", "Yes", null);
        QuestionTree q10 = new QuestionTree("Are your curls bouncy or do your curls look less curly?", "No",
                new QuestionTree[] { q12, genericDry });

        QuestionTree sunDamage = new QuestionTree("Explore protective styling to help retain moisture", "Yes", null);
        QuestionTree q9 = new QuestionTree("Do you sleep with a cotton pillow case?", "No",
                new QuestionTree[] { satinPillowcase, q10 });

        QuestionTree colorDamage = new QuestionTree("Color Damage - Be patient and gentle when handling your hair", "Recently put color in your hair", null);
        QuestionTree heatDamage = new QuestionTree("Heat Damage - Always use a heat protectant when styling with heat.", "Used direct heat on your hair", null);
        QuestionTree swimmingDamage = new QuestionTree("Protect your hair, when going swimming, with conditioner and a swim cap or use of " +
                "a product specifically designed for sun/chlorine/salt.", "Been swimming", null);
        QuestionTree q8 = new QuestionTree("Have you been in the sun a lot?", "None of the above",
                new QuestionTree[] { sunDamage, q9 });

        QuestionTree q5 = new QuestionTree("Too much", "more than 3 times a week", null);
        QuestionTree q6 = new QuestionTree("Which of these apply to you the most:", "1-3 times a week",
                new QuestionTree[] { colorDamage, heatDamage, swimmingDamage, q8 });
        QuestionTree q7 = new QuestionTree("Not enough", "less often",
                null);

        QuestionTree q15 = new QuestionTree("Do you have an itchy scalp?", "No",
                new QuestionTree[] { dryScalp, healthyHair });

        QuestionTree q2 = new QuestionTree("How often do you shampoo?", "Dry",
                new QuestionTree[] { q5, q6, q7 });

        QuestionTree breakage = new QuestionTree("Breakage", "No", null);
        QuestionTree q16 = new QuestionTree("Which of these apply to you the most:", "Yes",
                new QuestionTree[] { colorDamage, heatDamage, swimmingDamage, q8 });
        QuestionTree q14 = new QuestionTree("Do you have split ends?", "Yes",
                new QuestionTree[] { q16, breakage } );

        QuestionTree q3 = new QuestionTree("Which of", "Oily",
                new QuestionTree[] {});
        QuestionTree q4 = new QuestionTree("Are you having any breakage?", "Soft",
                new QuestionTree[] { q14, q15 });

        hairCheckupQuestionTree = new QuestionTree("What does your hair feel like right now?", "",
                new QuestionTree[] { q2, q3, q4 });


    }

    public void setHairTypeQuestions() {
        QuestionTree twoA = new QuestionTree("Your Hair Type is 2A", "Close to Straight", null);
        QuestionTree twoB = new QuestionTree("Your Hair Type is 2B", "Waves Start Below Eyes", null);
        QuestionTree twoC = new QuestionTree("Your Hair Type is 2C", "Waves Start at Root", null);
        QuestionTree twoCb = new QuestionTree("Your Hair Type is 2C", "Defined S Shaped Waves", null);
        QuestionTree threeA = new QuestionTree("Your Hair Type is 3A", "Sidewalk Chalk Width", null);
        QuestionTree threeB = new QuestionTree("Your Hair Type is 3B", "Ringlets/Tight Corkscrews", null);
        QuestionTree threeC = new QuestionTree("Your Hair Type is 3C", "Pencil or Straw Sized", null);
        QuestionTree fourA = new QuestionTree("Your Hair Type is 4A", "Crochet Needle Size", null);
        QuestionTree fourB = new QuestionTree("Your Hair Type is 4B", "Sharp Angles", null);
        QuestionTree fourC = new QuestionTree("Your Hair Type is 4C", "Undefined, Wont' Clump", null);

        QuestionTree question10 = new QuestionTree ("What do your coils look like?", "Tight Z Shaped Coils",
                new QuestionTree[]{ fourB, fourC });

        QuestionTree question9b = new QuestionTree ("How wide are your curls?", "Tight S Shaped Coils",
                new QuestionTree[]{ threeC, fourA });

        QuestionTree question9a = new QuestionTree ("How wide are your curls?", "Sharpy Pen or Samller",
                new QuestionTree[]{ threeB, threeC });

        QuestionTree question8 = new QuestionTree ("What shape are your coils?", "Tight Coils",
                new QuestionTree[]{ question9b, question10 });

        QuestionTree question7 = new QuestionTree ("How small are your curls?", "Small Curls",
                new QuestionTree[]{ question9a, question9b });

        QuestionTree question6 = new QuestionTree ("How big are your curls?", "Loose, Big Curls",
                new QuestionTree[]{ twoCb, threeA });

        QuestionTree question5 = new QuestionTree ("What do your curls look like?", "Curly or Coily",
                new QuestionTree[]{ question6, question7, question8 });

        QuestionTree question4 = new QuestionTree ("How do your waves behave?", "Defined or Loose S Shaped Waves",
                new QuestionTree[]{ twoB, twoC });

        QuestionTree question3 = new QuestionTree ("What shape are your waves?", "Loose & Lazy",
                new QuestionTree[]{ twoA, question4 });

        QuestionTree question2 = new QuestionTree ("What do your waves look like?", "Wavy",
                new QuestionTree[]{ question3, question4 });

        hairTypeQuestionTree = new QuestionTree ("How would you describe your hair?", "Natural",
                new QuestionTree[]{ question2, question5 });

//        hairTypeQuestionTree = new QuestionTree("What is the current state of your hair?", null,
//                new QuestionTree[]{ question1, question1 });
    }

    public QuestionTree getHairTypeQuestionTree() {
        return hairTypeQuestionTree;
    }

    public QuestionTree getHairCheckupQuestionTree() {
        return hairCheckupQuestionTree;
    }
}

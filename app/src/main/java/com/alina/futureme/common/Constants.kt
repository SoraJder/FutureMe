package com.alina.futureme.common

object Constants {

    //Screens
    const val SIGN_IN_SCREEN = "Sign in"
    const val SIGN_UP_SCREEN = "Sign up"
    const val HOME_SCREEN = "Home"
    const val FORGOT_PASSWORD_SCREEN = "Forgot password"
    const val VERIFY_EMAIL_SCREEN = "Verify email"
    const val ONBOARD_SCREEN = "Onboard"
    const val PROFILE_SCREEN = "Profile"
    const val WRITE_LETTER_SCREEN = "Write Letter"
    const val READ_LETTER_SCREEN = "Read Letter"
    const val SEND_INSTANT_LETTER_SCREEN = "Send Instant Letter"
    const val SEE_YOUR_LETTERS_SCREEN="See Your Letters"
    const val LOADING_SCREEN = "Loading"
    const val SEE_LETTER_SCREEN="See Letter"
    const val SEE_LETTER_SCREEN_ID="id"

    //google web client id
    const val WEB_CLIENT_ID =
        "556664704135-7kgiud2vuhpq7conl72ltmsc9d9f80ia.apps.googleusercontent.com"

    //data store
    const val ONBOARD_PREFERENCE = "onboard_preference"
    const val ONBOARD_KEY = "onboard_completed"

    //on board text
    const val FIRST_PAGE_TITLE = "Share and discover"
    const val FIRST_PAGE_DESCRIPTION =
        "Share your letter with the world (only if you wish) and read the amazing letter that others sent to their future selves."

    const val SECOND_PAGE_TITLE = "Send it to the future"
    const val SECOND_PAGE_DESCRIPTION =
        "On your chosen date, we'll deliver your letter from the past for you to read and reflect. You can also add your friends and family as additional recipients."

    const val THIRD_PAGE_TITLE = "Write a letter"
    const val THIRD_PAGE_DESCRIPTION =
        "Write a letter to 'Future Me' and choose a date for it to be delivered, such as a birthday, graduation or the other special occasion."

    // bottom sheet tips
    const val BOTTOM_SHEET_EXPLANATION = "Tap to apply this template to your letter"

    const val ACHIEVE_TITLE = "Write down some goals you want to achieve over the next months"
    const val FUTURE_ADVICE_TITLE = "Give future you some life advice to remember"
    const val GRATEFUL_TITLE = "Write a list of things you are grateful for"
    const val PREDICTION_TITLE = "Make a prediction about your life"
    const val FAVORITE_TITLE = "Give your future self a list of your current favorite things"

    const val ACHIEVE_TEXT =
        "Dear FutureMe,\n\nI commit to achieving the following goals in three months' time \n\n" +
                "Goal 1:\nI will achieve this by...\n\nGoal 2:\nI will achieve this by...\n\nGoal 3:\nI will achieve this by..."
    const val PREDICTION_TEXT = "Dear FutureMe, \n" +
            "\n" +
            "When I receive this letter I hope to be: \n" +
            "\n" +
            "Living in... \n" +
            "\n" +
            "Focusing on... \n" +
            "\n" +
            "Working at... \n" +
            "\n" +
            "Taking joy in... \n" +
            "\n" +
            "Learning more about... \n" +
            "\n" +
            "Spending time with... \n" +
            "\n" +
            "Spending my money on..."
    const val ADVICE_TEXT = "Dear FutureMe, \n" +
            "\n" +
            "Life can get overwhelming and sometimes we lose sight of what is important. So, I thought I would send you some important advice worth remembering. \n" +
            "\n" +
            "Advice about friendship: \n" +
            "\n" +
            "Advice about love: \n" +
            "\n" +
            "Advice about health: \n" +
            "\n" +
            "Advice about work-life balance: \n" +
            "\n" +
            "Advice about family: \n" +
            "\n" +
            "Advice about happiness: \n" +
            "\n" +
            "Advice about resilience:"
    const val GRATITUDE_TEXT = "Dear FutureMe, \n" +
            "\n" +
            "Sometimes the challenges of life can get you down. So, I'm sending you a list of things that I am grateful for today. I hope this will remind you to appreciate the simple positive things that every day brings. \n" +
            "\n" +
            "My Gratitude List \n" +
            "\n" +
            "1. \n" +
            "2. \n" +
            "3. \n" +
            "4. \n" +
            "5."
    const val FAVORITE_TEXT = "Dear FutureMe, \n" +
            "\n" +
            "These are a few of my (current) favorite things. I wonder if anything has changed? \n" +
            "\n" +
            "1. Favorite song/album/artist \n" +
            "\n" +
            "2. TV show I am currently binge watching \n" +
            "\n" +
            "3. Favorite book \n" +
            "\n" +
            "4. Favorite person to hang out with \n" +
            "\n" +
            "5. Best movie I have seen lately \n" +
            "\n" +
            "6. Favorite thing to do on the weekend \n" +
            "\n" +
            "7. Go-to snack \n" +
            "\n" +
            "8. Most memorable quote I heard recently \n" +
            "\n" +
            "9. Four things I would take to a dessert island \n" +
            "\n" +
            "10. Favorite person to hang out with"

    //LETTER ROW TABS
    const val POPULAR_TAB = "Popular"
    const val RECENT_TAB = "Recent"
    const val LIKED_TAB = "Liked"

    //SEND INSTANT LETTERS
    const val BIRTHDAY_SUBJECT="Happy Birthday!"
    const val BIRTHDAY_TEXT="Hi ,\n\n" +
            "Happy birthday friend! I want to wish you very warm congratulations on your special " +
            "day and offer my best wishes for many, many more great birthdays to come in your future.\n\n" +
            "I hope this particular birthday is the best one ever for you. Knowing you, you " +
            "probably have a list of exciting things planned to do this year in order to celebrate " +
            "it with your wonderful family. Keep this in mind: while everybody grows old, not " +
            "everyone grows up!\n\n" +
            "Sounds like me and you, right? Whenever I need to put a quick smile on my face, I " +
            "just think about all the good times we've shared over the years. You look as young " +
            "and beautiful as the first day I met you. Did you discover the fountain of youth'?\n\n" +
            "Also, I want to thank you for being there for me this past year when I really needed " +
            "help. But, that's what friends are for, and you're a good one. Please know that I " +
            "will always be there for you too.\n\n" +
            "Here's to wishing you a long, healthy, and prosperous life my friend!\n\n" +
            "Again, happy birthday.\n\n" +
            "Sincerely,\n"

    const val PARENTS_SUBJECT="How are you, dear parents?"
    const val PARENTS_TEXT="Dear Parents!\n\n" +
            "I thought I'd take I'd take a couple of minutes too see how you're doing.\n\n" +
            "Everything is fine with me and life is actively moving forward. Recently, I miss my home " +
            "very much and about your mentor advices and interesting stories.\n\n" +
            "How are you? Tell us what's new and how are you doing?\n\n" +
            "Sincerely,\n"

    const val LOVER_SUBJECT="Love you"
    const val LOVER_TEXT="My dear,\n\n" +
            "As you know, I am not much with words. When it comes to times of great importance, " +
            "I tend to muddle the message and never convey the full impact of my feelings.\n\n" +
            "That is why I am using the written word to convey this message, because it is one of the " +
            "most important messages I could want to convey.\n\n" +
            "That message is this - I love you. I have loved you for a while now, and with each day," +
            "my love seems to grow and solidify. It has reached the point that I feel my love for you " +
            "physically, pounding in my heart and almost crystallizing in the air between us.\n\n" +
            "I hope that you feel this love as well, and that the message of this letter is pleasing " +
            "to you.\n\nLove,\n"
}
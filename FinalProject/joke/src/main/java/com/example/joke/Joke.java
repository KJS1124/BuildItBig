package com.example.joke;

import java.util.Random;

public class Joke {
    private static final String data[] = {"Girl: You would be a good dancer except for two things.\n" +
            "Boy: What are the two things?\n" +
            "Girl: Your feet.\n",
            "A family of mice were surprised by a big cat. Father Mouse jumped and and said, \"Bow-wow!\" The cat ran away. \"What was that, Father?\" asked Baby Mouse. \"Well, son, that's why it's important to learn a second language.\" ",
            "Patient: Doctor, I have a pain in my eye whenever I drink tea.\n" +
                    "Doctor: Take the spoon out of the mug before you drink. ",
            "Patient: Doctor! You've got to help me! Nobody ever listens to me. No one ever pays any attention to what I have to say.\n" +
                    "\n" +
                    "Doctor: Next please! ",
            "A: Just look at that young person with the short hair and blue jeans. Is it a boy or a girl?\n" +
                    "B: It's a girl. She's my daughter.\n" +
                    "A: Oh, I'm sorry, sir. I didn't know that you were her father.\n" +
                    "B: I'm not. I'm her mother. "
    };

    public static String getJokes() {
        return data[new Random().nextInt(data.length)];
    }
}

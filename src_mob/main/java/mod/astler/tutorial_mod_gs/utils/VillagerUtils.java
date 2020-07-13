package mod.astler.tutorial_mod_gs.utils;

import java.util.Random;

public class VillagerUtils  {

    public static String getRandomMaleName() {
        switch(new Random().nextInt(10)+1)
        {
            case 1: return "Alex";
            case 2: return "Vlad";
            case 3: return "Aaron";
            case 4: return "Vitally";
            case 5: return "Connor";
            case 6: return "Ed";
            case 7: return "Fin";
            case 8: return "Astler";
            case 9: return "Jeff";
            case 10: return "Jonathan";
            default: return "Leo";

        }
    }

    public static String getRandomFemaleName() {
        switch(new Random().nextInt(10)+1)
        {
            case 1: return "Alisa";
            case 2: return "Belle";
            case 3: return "Cadence";
            case 4: return "Dory";
            case 5: return "Elsa";
            case 6: return "Fiona";
            case 7: return "Gilda";
            case 8: return "Harmony";
            case 9: return "Ivy";
            case 10: return "Jasmin";
            default: return "Kira";

        }
    }

    public static String getRandomFamilyName() {
        switch(new Random().nextInt(10)+1)
        {
            case 1: return "Smith";
            case 2: return "Jones";
            case 3: return "Brown";
            case 4: return "Miller";
            case 5: return "White";
            case 6: return "Thompson";
            case 7: return "Hill";
            case 8: return "Allen";
            case 9: return "Lewis";
            case 10: return "Adams";
            default: return "Green";

        }
    }

    public static String getRandomPlayerSkin(int gender) {

        String result = "";

        if(gender == 1) {
            result += "male/";

            switch(new Random().nextInt(10)+1)
            {
                case 1: result+="irish_male"; break;
                case 2: result+="male_1"; break;
                case 3: result+="male_2"; break;
                case 4: result+="male_3"; break;
                case 5: result+="old_male"; break;
                case 6: result+="steve_0"; break;
                case 7: result+="steve_1"; break;
                case 8: result+="steve_2"; break;
                case 9: result+="male_4"; break;
                case 10: result+="hunter_1"; break;
                default: result+="steve_0"; break;
            }

        }
        else if(gender == 0) {
            result += "female/";

            switch(new Random().nextInt(10)+1)
            {
                case 1: result+="female_1"; break;
                case 2: result+="female_2"; break;
                case 3: result+="female_3"; break;
                case 4: result+="female_4"; break;
                case 5: result+="female_5"; break;
                case 6: result+="female_6"; break;
                case 7: result+="female_7"; break;
                case 8: result+="female_8"; break;
                case 9: result+="female_9"; break;
                case 10: result+="female_10"; break;
                default: result+="female_1"; break;
            }
        }

        return result;
    }
}

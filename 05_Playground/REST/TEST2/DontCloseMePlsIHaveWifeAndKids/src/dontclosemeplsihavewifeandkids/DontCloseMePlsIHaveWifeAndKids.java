package dontclosemeplsihavewifeandkids;


public class DontCloseMePlsIHaveWifeAndKids {

    public static void testMenuKlient(){
        MenuKlient jedalen = new MenuKlient();

        System.out.println("id pizze: "+jedalen.postFood("pizza", Integer.TYPE));
        System.out.println("id rezna: " +jedalen.postFood("rezen", Integer.TYPE));
        System.out.println("id salatu: " +jedalen.postFood("zemiakovy salat", Integer.TYPE));
        System.out.println("\n");
        int n = jedalen.getCount(Integer.TYPE);
        
        for (int i = 0; i<n; i++) {
            System.out.println(i+": "+jedalen.getFoodByIndex(i+""));
        }
        jedalen.deleteFoodByIndex(2+"");
        System.out.println("\nVymazane id=2");
        for (int i = 0; i < n-1; i++) {
            System.out.println(i + ": " + jedalen.getFoodByIndex(i + ""));
        }
    }
   
    public static void testMenumapKlient(){
        MenumapKlient jedalenMap = new MenumapKlient();
        jedalenMap.putDescriptionByName("popis pizze", "pondelok", "pizza");
        jedalenMap.putDescriptionByName("popis svieckovej", "pondelok", "svieckova");
        jedalenMap.putDescriptionByName("popis rezna", "utorok", "rezen");
        jedalenMap.putDescriptionByName("popis rizota", "streda", "rizoto");
        jedalenMap.putDescriptionByName("popis vyprazaneho syra", "pondelok", "vyprazany syr");
        jedalenMap.putDescriptionByName("popis vyprazaneho syra", "utorok", "vyprazany syr");
        jedalenMap.putDescriptionByName("popis vyprazaneho syra", "streda", "vyprazany syr");
        jedalenMap.putDescriptionByName("popis gulasa", "pondelok", "gulas");

        System.out.println(jedalenMap.getAllFoods(null));
        System.out.println("\n\n");
        System.out.println(jedalenMap.getAllFoods("vyprazany syr"));

        System.out.println("\n\n");

        jedalenMap.putDescriptionByName("novy popis rizota", "pondelok", "rizoto");
        System.out.println(jedalenMap.getDescriptionByName("pondelok", "rizoto"));
        System.out.println(jedalenMap.getDescriptionByName("streda", "rizoto"));

        jedalenMap.deleteMenuByDay("pondelok");
        System.out.println(jedalenMap.getAllFoods(null));

        jedalenMap.deleteFoodByName("utorok", "rezen");
        System.out.println(jedalenMap.getAllFoods(null));
    }
    
    public static void testSlovnikCv9Klient(){
        Cv9Slovnik slovnik = new Cv9Slovnik();
        
        slovnik.putText("jablko", "sk", "apple");
        slovnik.putText("ahoj", "sk", "hello");
        slovnik.putText("zabi ma", "sk", "kill me");
        slovnik.putText("slovo po cesky", "cz", "word");
        slovnik.putText("ja ti uz neviem", "cz", "idk");
        
        System.out.println(slovnik.getTranslation("sk", "apple"));
        slovnik.deleteLanguage("sss");
        slovnik.deleteLanguage("cz");
        slovnik.deleteTranslation("cz", "apple");
        slovnik.deleteTranslation("sk", "kill me");
        System.out.println(slovnik.getLanguageTranslations("sk"));
        System.out.println(slovnik.getLanguageTranslations("cz"));

    }
    public static void testMenuCv9Klient(){}
    
    public static void testCv10Menu1Klient(){
        Cv10menu1 cv10Menu1 = new Cv10menu1();
//        cv10Menu1.addNewJedlo(new Jedlo(), String.class);
//        System.out.println(cv10Menu1.getMenu(String.class));
//        cv10Menu1.updateJedloByIndex(new Jedlo("pizza",2.5), "0");
//        cv10Menu1.deleteJedloByIndex("1");
//        System.out.println(cv10Menu1.addNewJedlo("abc", String.class));
//        System.out.println(cv10Menu1.addNewJedlo(new Jedlo(),Integer.class));
//        cv10Menu1.addNewJedlo(new Jedlo("gulas",2.2),String.class);
//        cv10Menu1.addNewJedlo(new Jedlo("maso",3.3),String.class);
//        cv10Menu1.addNewJedlo(new Jedlo("rezen",4.4),String.class);
        cv10Menu1.deleteJedloByIndex("2");
        System.out.println(cv10Menu1.getJedloByIndex(String.class, "2"));
        System.out.println(cv10Menu1.getJedloByIndex(String.class, "3"));
    }
    public static void testCv10Menu2Klient(){
        Cv10Menu2 cv10Menu2 = new Cv10Menu2();
        cv10Menu2.putJedlo(new Jedlo("halusky",1), "halusky");
        cv10Menu2.putJedlo(new Jedlo("syr",2), "syr");
        cv10Menu2.deleteJedlo("syr");
        System.out.println(cv10Menu2.getJedlo(String.class, "syr"));
        System.out.println(cv10Menu2.getJedlo(String.class, "halusky"));
//        System.out.println(cv10Menu2.getMenu(String.class));
    }
    public static void testCv10Menu3Klient(){
        Cv10Menu3 cv10Menu3 = new Cv10Menu3();
        cv10Menu3.putFoodByDayIndex(new Jedlo("halusky",1), "PON", "1");
        cv10Menu3.putFoodByDayIndex(new Jedlo("pizza",2), "UT", "1");
        cv10Menu3.putFoodByDayIndex(new Jedlo("pizza na id2",2), "UT", "2");
        cv10Menu3.putFoodByDayIndex(new Jedlo("gulas",3), "STR", "3");
//        cv10Menu3.delFoodByDayIndex("UT", "1");
//        System.out.println(cv10Menu3.getWholeMenu(String.class));
        System.out.println(cv10Menu3.getFoodByDayIndex(String.class,"PON","1"));
    }
    
    public static void main(String[] args) {
        //menu - prednaska 9 video
        //testMenuKlient();
        
        //menumap - prednaska 9 video
        //testMenumapKlient();
        
        //slovnik - cv9
        testSlovnikCv9Klient();
        
        //menu - cv9
        //testMenuCv9Klient();
        
        //menu - cv10
//        testCv10Menu1Klient();
//        testCv10Menu2Klient();
//        testCv10Menu3Klient();
    }
}

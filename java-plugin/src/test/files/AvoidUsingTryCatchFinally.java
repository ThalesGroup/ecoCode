package fr.greencodeinitiative.java.checks;

public class AvoidUsingTryCatchFinally {
    String path = "hello.txt";

    public void test(){

        int i = 0;

        try { // Noncompliant {{Avoid the use of try-catch}}
            System.out.println(i);
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }
        finally {
            System.out.println("The 'try except' is finished");
        }
    }

    public void test2(){

        try{ // Noncompliant {{Avoid the use of try-catch}}

            File file = new File(path);
            // Créer l'objet File Reader
            FileReader fr = new FileReader(file);
            // Créer l'objet BufferedReader
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();

            String line;
            while((line = br.readLine()) != null)
            {
                // ajoute la ligne au buffer
                sb.append(line);
                sb.append("\n");
            }
        }
        catch (Exception e){
            System.out.println("No such file " + path);
        }
        finally {
            fr.close();
            System.out.println("Contenu du fichier: ");
            System.out.println(sb.toString());
        }
    }

    void test3(){
        BufferReader reader = new BufferReader(new FileReader(path));
        String line;

        while(line = reader.readLine() != null){
            System.out.println(line);
        }
        reader.close();
    }
}
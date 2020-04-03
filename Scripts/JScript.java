
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;


class JacocoFileColumns{
    String classnames;
    String branchCoverage;
    String lineCoverage;
    String branchMissed;
    String totalBranch;
    String branchCovPercent;
    String branchCovDecimal;


    JacocoFileColumns(){
        classnames ="";
        branchCoverage="";
        lineCoverage="";
        branchMissed="";
        totalBranch="";
        branchCovPercent="";
        branchCovDecimal="";
    }
}
public class JScript {

    ArrayList<String> pitestClasses = new ArrayList<String>();

    public void parsePitestingFile (String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("PitFileExtracted.csv")))) {

            File inputFile = new File(filePath + "/index.html");

            Document doc = Jsoup.parse(inputFile, "UTF-8", "");

            Elements packages = doc.select("a[href]");  //a tags with href attribute in them  i.e.  <a href .....>

            writer.append("Sr.No");
            writer.append(",");
            writer.append("Class Name");
            writer.append(",");
            writer.append("Line Coverage");
            writer.append(",");
            writer.append("Mutation Score");
            writer.append(",");
            writer.append("Line Coverage decimal");
            writer.append(",");
            writer.append("Mutation Score Decimal");
            writer.append("\n");
            int noCofClasses=1;

            for (Element Package : packages) {


                if (!(Package.text().equals("PIT"))) {

                    String path = Package.text();
                    File nested = new File(filePath + "/" + path + "/index.html");

                    Document nestedDoc = Jsoup.parse(nested, "UTF-8", "");

                    Elements percentages = nestedDoc.select("div.coverage_percentage");
                    Elements ClassNames = nestedDoc.select("a[href]");

                    ClassNames.remove(ClassNames.size() - 1);

//                  int percentagesSize = percentages.size();
                    int ClassNamesSize = ClassNames.size();

                    System.out.println("Package Name: " + Package.text());
                    float linCov=0;
                    float MutationScore=0;
                    for (int i = 0; i < ClassNamesSize; i++) {

                        Element Class = ClassNames.get(i);

                        Element lineCov = percentages.get(i * 2);
                        Element mutationScore = percentages.get(i * 2 + 1);

                        System.out.println(noCofClasses+": "+Class.text() + "           " + lineCov.text() + "      " + mutationScore.text());


                        String className = Class.text();

                        className = className.substring(0,className.length()-5);
                        pitestClasses.add(className);
                        String lineCoverage = lineCov.text();

                        lineCoverage = lineCoverage.substring(0,lineCoverage.length()-1);

                        linCov = Integer.parseInt(lineCoverage);
                        linCov = linCov/100;

                        String linCoverageDecimal = Float.toString(linCov);


                        String mutScore = mutationScore.text();

                        mutScore = mutScore.substring(0,mutScore.length()-1);
                         MutationScore = Integer.parseInt(mutScore);
                        MutationScore = MutationScore/100;
                        String mutScoreDecimal = Float.toString(MutationScore);


                        writer.append(Integer.toString(noCofClasses));
                        writer.append(",");
                        writer.append(className);
                        writer.append(",");
                        writer.append(lineCoverage);
                        writer.append(",");
                        writer.append(mutScore);
                        writer.append(",");
                        writer.append(linCoverageDecimal);
                        writer.append(",");
                        writer.append(mutScoreDecimal);
                        writer.append("\n");
                        noCofClasses++;

                    }

                    System.out.println("-----------");
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readJacocoCsv(String jacocoFilePath) {




        File JacocoFileOriginal = new File(jacocoFilePath + "/jacoco.csv");
        try(BufferedReader reader = new BufferedReader(new FileReader(JacocoFileOriginal))){
            String line=reader.readLine();
            String[] lineArray={};
            List<JacocoFileColumns> classandBranchCoverages = new ArrayList<JacocoFileColumns>();

            int noOfJacocoClasses=1;


            while( (line = reader.readLine()) != null) {

                lineArray = line.split(",");

                JacocoFileColumns obj = new JacocoFileColumns();
                obj.classnames = lineArray[2];
                obj.branchMissed = lineArray[5];
                obj.branchCoverage = lineArray[6];
                obj.lineCoverage = lineArray[8];
                Float temp = Float.parseFloat(obj.branchCoverage) + Integer.parseInt(obj.branchMissed);
                obj.totalBranch =  Float.toString(temp);
                if(temp!=0) {

                    Float temp1 = Float.parseFloat(obj.branchCoverage) / temp;
                    obj.branchCovDecimal = Float.toString(temp1);
                }
                else{
                    obj.branchCovDecimal = Integer.toString(0);
                }

                obj.branchCovPercent = Float.toString(Float.parseFloat(obj.branchCovDecimal)*100);

                classandBranchCoverages.add(obj);

                noOfJacocoClasses++;

            }

            List<JacocoFileColumns>  JacocoCopy  = new ArrayList<JacocoFileColumns>(classandBranchCoverages);



            for (int i=0; i < JacocoCopy.size(); i++) {
                String ClassinJacoco = JacocoCopy.get(i).classnames;
                if (  ! (pitestClasses.contains(ClassinJacoco))  ){

                    JacocoCopy.remove(i);
                    i--;
                }
            }

            FileWriter csvWriter = new FileWriter("new.csv");


            csvWriter.write("Sr.No");
            csvWriter.write(",");
            csvWriter.write("Class Name");
            csvWriter.write(",");
            csvWriter.write("Line Coverage");
            csvWriter.write(",");
            csvWriter.write("Branch Coverage");
            csvWriter.write(",");
            csvWriter.write("Branch Missed");
            csvWriter.write(",");
            csvWriter.write("Branch Missed+Covered");
            csvWriter.write(",");
            csvWriter.write("BranchCoveragePercentageDecimal");
            csvWriter.write(",");
            csvWriter.write("BranchCoveragePercentage");

            csvWriter.write("\n");
            int noOfClassesInJaCoCoSameAsPitest=0;

            for(int i =0; i<JacocoCopy.size(); i++) {

                String className = (String) JacocoCopy.get(i).classnames;
                String lineCov = (String)JacocoCopy.get(i).lineCoverage;
                String BranchCov =(String) JacocoCopy.get(i).branchCoverage;
                String BranchCoverageDecimal = (String)JacocoCopy.get(i).branchCovDecimal;
                String BranchCoveragePercentage = (String)JacocoCopy.get(i).branchCovPercent;
                String BranchMissed = (String)JacocoCopy.get(i).branchMissed;
                String Branchstotal = (String)JacocoCopy.get(i).totalBranch;

                noOfClassesInJaCoCoSameAsPitest++;


                csvWriter.append(Integer.toString(noOfClassesInJaCoCoSameAsPitest));
                csvWriter.append(",");
                csvWriter.append(className);
                csvWriter.append(",");
                csvWriter.append(lineCov);
                csvWriter.append(",");
                csvWriter.append(BranchCov);
                csvWriter.append(",");
                csvWriter.append(BranchMissed);
                csvWriter.append(",");
                csvWriter.append(Branchstotal);
                csvWriter.append(",");
                csvWriter.append(BranchCoverageDecimal);
                csvWriter.append(",");
                csvWriter.append(BranchCoveragePercentage);
                csvWriter.append("\n");

            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("noOfClassesInJaCoCoSameAsPitest : "+ noOfClassesInJaCoCoSameAsPitest);

            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");


            System.out.println("Jacoco Classes");
            for(int i =0;i<classandBranchCoverages.size(); i++){
                System.out.println((i+1)+": "+classandBranchCoverages.get(i).classnames);

            }
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");

            System.out.println("Jacoco Classes same as Pitest");
            for(int i =0;i<JacocoCopy.size(); i++){
                System.out.println((i+1)+": "+JacocoCopy.get(i).classnames);

            }

            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
        public static void main(String[] args) throws FileNotFoundException {

            System.out.println("Enter the folder path in which Pitest index.html file is present");
            Scanner in = new Scanner(System.in);
            String filePath = in.nextLine();
            JScript fp = new JScript();
            fp.parsePitestingFile(filePath);
            System.out.println("File Generated as Data1.csv in this project's directory");


            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");
            System.out.println("***********************************************************************************************************");

            System.out.println("Enter Jacoco CSV Path");
            String jacocoFilePath = in.nextLine();

            fp.readJacocoCsv(jacocoFilePath);

/*
            String test = "72";
            int a = Integer.parseInt(test);
            a = a/2;
            System.out.println(a);
*/





        }
}
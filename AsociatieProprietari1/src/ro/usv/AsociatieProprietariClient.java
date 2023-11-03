/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsociatieProprietariClient {

    public static void main(String[] args) throws IOException {

            PrintWriter print = new PrintWriter(System.out);

            AsociatieProprietariServ q = new AsociatieProprietariServ();

            String fis = "file asociatie.ser\n" +
                    "clear\n" +
                    "add L  1    51. 1990 Zorilor 10  A 2 12 3 \n" +
                    "add SF 190  50. 1990 \"Zorilor Anului\" 9  A  2  12 \"S.C. Tester Prim\" 11223344 \n" +
                    //"rem completati ... de pe linia urmatoare cu informatiile din text ref. la ap. cu id=1\n" +
                    "add L  1 51. 1990 Zorilor 10  A 2 12 3  \n" +
                    //"rem completati ... de pe linia urmatoare cu informatiile din text ref. la ap. cu id=101\n" +
                    "add L  101 60. 2012 Zorilor 15 A 1 5 2\n" +
                    "list\n" +
                    "list 1\n" +
                    "list 10\n" +
                    "count L\n" +
                    "count SF\n" +
                    "count ?\n" +
                    "count\n" +
                    "rem se elimina ap. cu id=190\n" +
                    "delete 190\n" +
                    "count SF\n" +
                    "delete 190\n" +
                    "count SF\n" +
                    "rem se face din nou add 190\n" +
                    "add SF 190  50. 1990 Zorilor 9  A  2  12 \"S.C. Tester Prim\" 11223344 \n" +
                    "add SF 190  50. 1990 Zorilor 9  A  2  12 \"S.C. Tester Prim\" 11223344 \n" +
                    "list 190\n" +
                    "newer 10\n" +
                    "newer  5\n" +
                    "newer  1\n" +
                    "sfg\n" +
                    "add\n" +
                    "countsurf L 50\n" +
                    "select 55 2\n" +
                    "expenses 1\n" +
                    "file asoc.ser gg\n" +
                    "street Zorilor\n" +
                    "street \"Stefan cel Mare\"\n" +
                    "rem se schimba mediul de stocare in asocNr1.ser\n" +
                    "file asocNr1.ser\n" +
                    "clear\n" +
                    "add L  1    51. 1990 Zorilor 10  A 2 12 3 \n" +
                    "add SF 190  50. 1990 Zorilor 9  A  2  12 \"S.C. Tester Prim\" 11223344 \n" +
                    "list\n" +
                    "stop";

            FileReader f = new FileReader(args[0]);
            Scanner r = new Scanner(fis);
            while (r.hasNextLine()) {
                String cmd = r.nextLine();
                String[] arg = cmd.trim().split("\\s+");
                print.println(cmd);
                //System.out.println(cmd);
                switch (arg[0].toLowerCase()) {
                    case "add":
                        try {
                            Pattern pat = Pattern.compile("\"([^\"]*)\"");
                            Matcher mat = pat.matcher(cmd);
                            StringBuffer sb = new StringBuffer();
                            while (mat.find()) {
                                mat.appendReplacement(sb, "");
                            }
                            mat.appendTail(sb);
                            String sb1 = sb.toString();
                            String[] argss = sb1.split("\\s+");
                            String IntreGhilimele = null;
                            ArrayList<Integer> indexuri = new ArrayList<>();
                            ArrayList<String> ghilimele = new ArrayList<>();
                            if (cmd.contains("\"")) {
                                for (int i = 0; i < cmd.length(); i++) {
                                    if (cmd.charAt(i) == '\"') {
                                        indexuri.add(i);
                                    }
                                }
                                if (indexuri.size() == 4) {
                                    ghilimele.add(cmd.substring(indexuri.get(0) + 1, indexuri.get(1)));
                                    ghilimele.add(cmd.substring(indexuri.get(2) + 1, indexuri.get(3)));
                                } else if (indexuri.size() == 2) {
                                    IntreGhilimele = cmd.substring(indexuri.get(0) + 1, indexuri.get(1));
                                }
                            }
                            if (argss.length == 11 || argss.length == 12 || argss.length == 10 || argss.length == 9) {
                                if (argss[1].equals("L")) {
                                    if (indexuri.size() == 0) {
                                        Scanner scan = new Scanner(argss[2]);
                                        if(Integer.parseInt(argss[2]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(2)");
                                        }
                                        if(Double.parseDouble(argss[3]) < 1 || Double.parseDouble(argss[3]) > Double.MAX_VALUE)
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(3)");
                                        }
                                        scan = new Scanner(argss[4]);
                                        if(Integer.parseInt(argss[4]) < 0 || Integer.parseInt(argss[4]) > Year.now().getValue() || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(4)");
                                        }
                                        scan = new Scanner(argss[8]);
                                        if(Integer.parseInt(argss[8]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(8)");
                                        }
                                        scan = new Scanner(argss[10]);
                                        if(Integer.parseInt(argss[10]) < 1 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(10)");
                                        }
                                        Locuinta l = new Locuinta(Integer.parseInt(argss[2]), Double.parseDouble(argss[3]), Integer.parseInt(argss[4]), argss[5], argss[6], argss[7], Integer.parseInt(argss[8]), argss[9], Integer.parseInt(argss[10]));
                                        q.saveApartament(l);
                                    } else if (indexuri.size() == 2) {
                                        Scanner scan = new Scanner(argss[2]);
                                        if(Integer.parseInt(argss[2]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(2)");
                                        }
                                        if(Double.parseDouble(argss[3]) < 1 || Double.parseDouble(argss[3]) > Double.MAX_VALUE)
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(3)");
                                        }
                                        scan = new Scanner(argss[4]);
                                        if(Integer.parseInt(argss[4]) < 0 || Integer.parseInt(argss[4]) > Year.now().getValue() || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(4)");
                                        }
                                        scan = new Scanner(argss[7]);
                                        if(Integer.parseInt(argss[7]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(8)");
                                        }
                                        scan = new Scanner(argss[9]);
                                        if(Integer.parseInt(argss[9]) < 1 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(10)");
                                        }
                                        Locuinta l = new Locuinta(Integer.parseInt(argss[2]), Double.parseDouble(argss[3]), Integer.parseInt(argss[4]), IntreGhilimele, argss[5], argss[6], Integer.parseInt(argss[7]), argss[8], Integer.parseInt(argss[9]));
                                        q.saveApartament(l);
                                    }
                                }
                                if (argss[1].equals("SF")) {
                                    if (indexuri.size() == 2) {
                                        Scanner scan = new Scanner(argss[2]);
                                        if(Integer.parseInt(argss[2]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(2)");
                                        }
                                        if(Double.parseDouble(argss[3]) < 1 || Double.parseDouble(argss[3]) > Double.MAX_VALUE)
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(3)");
                                        }
                                        scan = new Scanner(argss[4]);
                                        if(Integer.parseInt(argss[4]) < 0 || Integer.parseInt(argss[4]) > Year.now().getValue() || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(4)");
                                        }
                                        scan = new Scanner(argss[8]);
                                        if(Integer.parseInt(argss[8]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(8)");
                                        }
                                        scan = new Scanner(argss[10]);
                                        if(Long.parseLong(argss[10]) < 1 || !scan.hasNextLong())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(11)");
                                        }
                                        SediuFirma s = new SediuFirma(Integer.parseInt(argss[2]), Double.parseDouble(argss[3]), Integer.parseInt(argss[4]), argss[5], argss[6], argss[7], Integer.parseInt(argss[8]), argss[9], IntreGhilimele, Long.parseLong(argss[10]));
                                        q.saveApartament(s);
                                    } else if (indexuri.size() == 4) {
                                        Scanner scan = new Scanner(argss[2]);
                                        if(Integer.parseInt(argss[2]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(2)");
                                        }
                                        if(Double.parseDouble(argss[3]) < 1 || Double.parseDouble(argss[3]) > Double.MAX_VALUE)
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(3)");
                                        }
                                        scan = new Scanner(argss[4]);
                                        if(Integer.parseInt(argss[4]) < 0 || Integer.parseInt(argss[4]) > Year.now().getValue() || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(4)");
                                        }
                                        scan = new Scanner(argss[7]);
                                        if(Integer.parseInt(argss[7]) < 0 || !scan.hasNextInt())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(8)");
                                        }
                                        scan = new Scanner(argss[9]);
                                        if(Long.parseLong(argss[9]) < 1 || !scan.hasNextLong())
                                        {
                                            throw new IllegalArgumentException("Format incorect pentru parametru(11)");
                                        }
                                        SediuFirma s = new SediuFirma(Integer.parseInt(argss[2]), Double.parseDouble(argss[3]), Integer.parseInt(argss[4]), ghilimele.get(0), argss[5], argss[6], Integer.parseInt(argss[7]), argss[8], ghilimele.get(1), Long.parseLong(argss[9]));
                                        q.saveApartament(s);
                                    }
                                }
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "list":
                        try {
                            if (arg.length == 2 || arg.length == 1) {
                                if (arg.length == 1) {
                                    //System.out.println(q.getApartamentente());
                                    print.println(q.getApartamentente());
                                }
                                if (arg.length == 2) {
                                    if(q.getApartamentById(Integer.parseInt(arg[1])) == null)
                                    {
                                        throw new IllegalArgumentException("Nu exista apartament cu id=" + arg[1]);
                                    }
                                    Scanner scan = new Scanner(arg[1]);
                                    if(!scan.hasNextInt())
                                    {
                                        throw new IllegalArgumentException("Format incorect pentru parametru(1)");
                                    }
                                    //.println(q.getApartamentById(Integer.parseInt(arg[1])));
                                    print.println(q.getApartamentById(Integer.parseInt(arg[1])));
                                }
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "clear":
                        try {
                            if (arg.length == 1) {
                                q.deleteApartmente();
                                //System.out.println("S-au eliminat toate apartamentele");
                                print.println("S-au eliminat toate apartamentele");
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println(("Eroare. " + e.getMessage()));
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "delete":
                        try {
                            if (arg.length == 2 ) {
                                if(q.getApartamentById(Integer.parseInt(arg[1])) == null)
                                {
                                    throw new IllegalArgumentException("delete: obj cu id=" + arg[1] + " nu exista");
                                }
                                Scanner scan = new Scanner(arg[1]);
                                if(!scan.hasNextInt())
                                {
                                    throw new IllegalArgumentException("Format incorect pentru parametru(1)");
                                }
                                q.deleteApartment(Integer.parseInt(arg[1]));
                                //System.out.println("S-a eliminat apartamentul cu id " + arg[1]);
                                //print.println("S-a eliminat apartamentul cu id " + arg[1]);
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "file":
                        try {
                            if (arg.length == 2) {
                                q.setStocare(arg[1]);
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "rem":
                        try {
                            //System.out.println(cmd);
                            print.println(cmd);
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println(e.getMessage());
                            print.println(e.getMessage());
                            break;
                        }
                    case "stop":
                        try {
                            if (arg.length == 1) {
                                //System.out.println("La revedere!");
                                print.println("La revedere!");
                                print.close();
                                System.exit(0);
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "count":
                        try {
                            if (arg.length == 1 || arg.length == 2) {
                                if (arg.length == 2) {
                                    if (arg[1].equals("L")) {
                                        //System.out.println("Nr. locuinte: " + q.countApartamente("L"));
                                        print.println("Nr.locuinte: " + q.countApartamente("L"));
                                    } else if (arg[1].equals("SF")) {
                                        //System.out.println("Nr.sedii firme: " + q.countApartamente("SF"));
                                        print.println("Nr.sedii firme: " + q.countApartamente("SF"));
                                    } else {
                                        //System.out.println("Nu sunt apartamente de tipul " + arg[1]);
                                        print.println("Nu sunt apartamente de tipul " + arg[1]);
                                    }
                                }
                                if (arg.length == 1) {
                                    //System.out.println("Nr. apartamente: " + (q.countApartamente("L") + q.countApartamente("SF")));
                                    print.println("Nr.apartamente: " + (q.countApartamente("L") + q.countApartamente("SF")));
                                }
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "countsurf":
                        try{
                            if(arg.length == 3)
                            {
                                if(arg[1].equals("L"))
                                {
                                    print.println(q.getCountSurf("L", Double.parseDouble(arg[2])));
                                }
                                else if(arg[1].equals("SF"))
                                {
                                    print.println(q.getCountSurf("SF", Double.parseDouble(arg[2])));
                                }
                                else
                                {
                                    print.println("Nu sunt apartamente de tipul " + arg[1]);
                                }
                            }
                            else
                            {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                        }
                        catch(Exception e)
                        {
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "select":
                        try{
                            if(arg.length == 3)
                            {
                                print.println(q.selectEtajMax(Double.parseDouble(arg[1]), Integer.parseInt(arg[2])));
                            }
                            else
                            {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                        }
                        catch(Exception e)
                        {
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "expenses":
                        try{
                            if(arg.length == 2)
                            {
                                if(q.getApartamentById(Integer.parseInt(arg[1])) == null)
                                {
                                    throw new IllegalArgumentException("Nu exista apartament cu id=" + arg[1]);
                                }
                                print.println(q.getExpenses(Integer.parseInt(arg[1])));
                            }
                            else
                            {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                        }
                        catch(Exception e)
                        {
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "newer":
                        try {
                            if (arg.length == 2) {
                                Scanner scan = new Scanner(arg[1]);
                                if (Integer.parseInt(arg[1]) < 0 || !scan.hasNextInt()) {
                                    throw new IllegalArgumentException("Format incorect pentru parametru(1)");
                                } else {
                                    int an = Year.now().getValue() - Integer.parseInt(arg[1]);
                                    //System.out.println("Ap. construite cu cel mult " + arg[1] + " ani in urma: " + q.findIdsNewerThan(an));
                                    print.println("Ap. construite cu cel mult " + arg[1] + " ani in urma: " + q.findIdsNewerThan(an));
                                }
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    case "street":
                        try {
                            Pattern pats = Pattern.compile("\"([^\"]*)\"");
                            Matcher mats = pats.matcher(cmd);
                            StringBuffer sbs = new StringBuffer();
                            while (mats.find()) {
                                mats.appendReplacement(sbs, "");
                            }
                            mats.appendTail(sbs);
                            String sb1s = sbs.toString();
                            String[] argsss = sb1s.split("\\s+");
                            String IntreGhilimeles = null;
                            ArrayList<Integer> indexuris = new ArrayList<>();
                            if (cmd.contains("\"")) {
                                for (int i = 0; i < cmd.length(); i++) {
                                    if (cmd.charAt(i) == '\"') {
                                        indexuris.add(i);
                                    }
                                }
                                IntreGhilimeles = cmd.substring(indexuris.get(0) + 1, indexuris.get(1));
                            }
                            if (argsss.length == 2 || (argsss.length == 1 && IntreGhilimeles != null)) {
                                if (indexuris.size() == 0) {
                                    //System.out.println("Ap. str. " + arg[1] + ": " + q.findIdsByStreet(arg[1]));
                                    print.println("Ap. str. " + arg[1] + "" + q.findIdsByStreet(arg[1]));
                                } else if (indexuris.size() == 2) {
                                    //System.out.println("Ap. str. " + IntreGhilimeles + ": " + q.findIdsByStreet(IntreGhilimeles));
                                    print.println("Ap. str. " + IntreGhilimeles + "" + q.findIdsByStreet(IntreGhilimeles));
                                }
                            } else {
                                throw new IllegalArgumentException("Numarul parametrilor nu este corect");
                            }
                            break;
                        }
                        catch (Exception e)
                        {
                            //System.out.println("Eroare. " + e.getMessage());
                            print.println("Eroare. " + e.getMessage());
                            break;
                        }
                    default :
                        //System.out.println("Eroare. Comanda neimplementata");
                        print.println("Eroare. Comanda neimplementata");
                        break;

                }
            }

            print.close();
    }
}

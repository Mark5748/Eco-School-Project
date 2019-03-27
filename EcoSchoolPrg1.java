
/*************************************************************************************************
  * @Mark Arruda, Jocelyn Nurtanto, Matthew Vinzon, Kostner Woon-Fat  @ Monday, June 18th, 2018
  * This program is a data base of energy consumption, throughout the shcool,
  * it has the info of each room in teh school, and what major items use energy in Watts
  * ********************************************************************************************/

import java.util.*;
import java.io.*;
//import java.text.*;



class EcoSchoolPrg1 implements WattsConstant {
  static ArrayList<Classroom> rooms = new ArrayList<Classroom>();
  Scanner input = new Scanner(System.in);
  static int r=0;//variable used when the room is decied
   static int t=1;
  
  //Kostner
  public static void main(String [] arg) {
    System.out.println("\t\t\t\tECOSKOOL");
    System.out.println("\tThis Program is to determine the energy consumption of the School, St.Joseph Secondary School. It separates the energy consuption by room, and also the whole school.");
    System.out.println("\t\tBy:Mark Arruda, Jocelyn Nurtanto, Matthew Vinzon, Kostner Woon-Fat (AKA Team Wattkanda)");
    try{
      openFileMethod();
    }catch(IOException e){System.out.println(e.getMessage());}
    mainMenu();
  }//end main
  
  /*
   *  @Author:Kostner Woon-Fat    @Date: Monday, June 18th, 2018
   *  @param: none
   *  @return: none
   Method Description: This method is responsible for asking the user
   what room is to be displayed    */
  public static void mainMenu() {
    int choice;
    
    Scanner in = new Scanner(System.in);
    do {
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      System.out.println("\tWhat Action would you like to perform? Please choose the number corresponding to the correct option:");
      System.out.println("\t1. Enter a room name/number");
      System.out.println("\t2. Display top 5 classrooms that consume the most energy");
      System.out.println("\t3. Display school's total watts, electricity cost, GHG emmisons, and number of trees for one school day");
      System.out.println("\t4. Display school's cost, emmison and number of trees for one school .year");
      System.out.println("\t5. Display a list of all the rooms in the School");
      System.out.println("\t0. Exit the program");
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      while (!in.hasNextInt()) {
        System.out.println("Please enter an integer value.");
        in.nextLine();//may need to take out if pop out twice
      }
      choice = in.nextInt();
      switch (choice) {
        case 1:
          String room;
          System.out.println("---------------------------------------------------------------------------------------------------------------------");
          System.out.println("Enter room name/number");
          //  while (!in.hasNext()) {
          //   System.out.println("Please enter a value.");
          in.nextLine();
          //  }
          room = in.nextLine();
          // System.out.println(room);
          for(int i=0; i<rooms.size();i++){
            if(room.equals(rooms.get(i).getRmNumber())){
              r=i;
              System.out.println("You have chosen the room "+rooms.get(r).getRmNumber());
              subMenu();
              break;
            }
            else{
              t=0;
            }
          }
          if(t==0){
            System.out.println("The room you entered was not in our database");
          }
          break;
          
        case 2:
          sortRooms();
          
          break;
        case 3:
          costDay();
          break;
        case 4:
          costYear();
          break;
        case 5:
          System.out.println("---------------------------------------------------------------------------------------------------------------------");
          for(int i=0; i<rooms.size();i++){
            System.out.println("\tRoom:"+rooms.get(i).getRmNumber());
          }
          System.out.println("---------------------------------------------------------------------------------------------------------------------");
          break;
        case 0:
          System.out.println("Goodbye!");
          break;
        default:
          System.out.println("Please enter a correct value");
          
      }
      
      
      
    } while (choice != 0);
    
  }//end of Main Menu
  
  
  
  /* @Author:Kostner @Date: Monday, June 18th, 2018
   @param:  none     @return: none
   * Method Description: This method is responsible for asking the user
   what stats about the room are to be displayed.
   */
  public static void subMenu() {
    Scanner in = new Scanner(System.in);
    int choice;
    do {
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      System.out.println("What action would you like to peform, please select the correct number for the corresponding option:");
      System.out.println("\t2. Display total wattage of room and room#/general applications (all units in Watt hours)");
      System.out.println("\t3. Display watts, plus Carbon Dioxide emission equivalents and trees needed to absorb the emission (all units in Watt hours)");
      System.out.println("\t4. Display specific applications");    
      System.out.println("\t5. Display total wattage of room and room#/specific applications ");
      System.out.println("\t6. Display cost of room for one day");
      System.out.println("\t7. Display cost of room for one year");
      System.out.println("\t9. Go back (Enter new room name or number)");
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      while (!in.hasNextInt()) {
        System.out.println("Please enter an integer value");
        in.nextLine();
      }
      choice = in.nextInt();
      switch (choice) {
        case 2:
          display();
          break;
        case 3:
          displayWatt();
          break;
        case 4:
          spefMenu();
          break;
        case 5:
          spefDisplay();
          break;
        case 6:
          costRoom();
          break;
        case 7:
          costRoomYear();
          break;
        case 9:
          System.out.println("Now going back to Main Menu");
          t=1;
          break;
        default:
          System.out.println("Please enter a correct value");
          // break;
      }
    } while (choice != 9);
  }
  
  /* Method used to display gernal lights,computers etc and their
   * watts
   *  @Author:Mark Arruda   @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void display() {//WIP
    int i=r; //so I dont have to change all the i back to r
    int totalLights=0, totalPC=0, totalMacs=0, totalPrinters=0,totalTV=0, totalProjects=0,totalAppliancesOther=0;
    
    totalLights = rooms.get(i).getNormLights() + rooms.get(i).getELights() + rooms.get(i).getPotlights();
    
    totalLights = totalLights +rooms.get(i).getLargePotlights()+ rooms.get(i).getIncandescent()+ rooms.get(i).getHalfCFL() + rooms.get(i).getGO10Bulbs();
    totalPC= rooms.get(i).getDell390() + rooms.get(i).getDell380() + rooms.get(i).getDell3020() + rooms.get(i).getDell3010() + rooms.get(i).getDell3040() +rooms.get(i).getHPSamsung ()+ rooms.get(i).getDell3050();
    totalMacs= rooms.get(i).getMac();
    totalProjects= rooms.get(i).getEpsonProj() + rooms.get(i).getBenqProj ()+ rooms.get(i).getSmartboard();
    totalPrinters= rooms.get(i).getOKIPriner() + rooms.get(i).getXerox() + rooms.get(i).getLexmarkPrinter();
    totalAppliancesOther= rooms.get(i).getFridge()+rooms.get(i).getMiniFridge()+ rooms.get(i).getFreezerBox() +rooms.get(i).getMicrowave()+rooms.get(i).getMultimediaCart() +rooms.get(i).getChromebookCart()+rooms.get(i).getVendingMachine();
    totalTV=rooms.get(i).getToshibaTV() +rooms.get(i).getSamsungTV()+rooms.get(i).getSharpTV();
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-5s", "Room");
    
    if(totalLights>0){
      System.out.printf("%-20s %-25s", "Number of Lights","Total Wattage of Lights");
    }//end of if
    if(totalPC>0){
      System.out.printf("%-20s %-27s", "Number of Computers","Total Wattage of Computers");
    }//end of if
    if(totalMacs>0){
      System.out.printf("%-15s %-25s", "Number of Macs","Total Wattage of Macs");
    }//end of if
    if(totalProjects>0){
      System.out.printf("%-25s %-30s", "Number of Projectors","Total Wattage of Projector");
    }//end of if
    if(totalPrinters>0){
      System.out.printf("%-20s %-30s", "Number of Printers","Total Wattage of Printers");
    }//end of if
    if(totalTV>0){
      System.out.printf("%-25s %-25s", "Number of TV","Total Wattage of TV");
    }//end of if
    if(totalAppliancesOther>0){
      System.out.printf("%-25s %-25s", "Number of Appliances","Total Wattage of Appliances ");
    }//end of if
    System.out.printf("%-25s\n","Total Wattage");
//Second Column
    System.out.printf("%-5s",rooms.get(r).getRmNumber());
    if(totalLights>0){
      System.out.printf("%-20d %-25.2f",totalLights, rooms.get(i).calcTotalLights());
    }//end of if
    if(totalPC>0){
      System.out.printf("%-20d %-27.2f", totalPC, rooms.get(i).calcTotalComputers());
    }//end of if
    if(totalMacs>0){
      System.out.printf("%-15d %-25.2f", totalMacs,(totalMacs*macWatt));
    }//end of if
    if(totalProjects>0){
      double wPro=0;
      if(rooms.get(i).getEpsonProj() >0){
        wPro=rooms.get(i).getEpsonProj()*epsProjWatt*9.0+wPro;
        // System.out.println("WPRO"+wPro);
      }//end of if
      if(rooms.get(i).getBenqProj() >0){
        wPro=rooms.get(i).getBenqProj()*benqProjWatt*9.0+wPro;
        //  System.out.println("WPRO"+wPro);
      }//end of if
      if(rooms.get(i).getSmartboard() >0){
        wPro=rooms.get(i).getSmartboard()*smartboardWatt*9.0+wPro;
        //System.out.println("WPRO"+wPro);
      }//end of if
      //System.out.println("WPRO"+wPro);
      System.out.printf("%-25d %-30.2f", totalProjects,wPro);
    }//end of if
    
    if(totalPrinters>0){
      double pPro=0;
      if(rooms.get(i).getOKIPriner() >0){
        pPro=rooms.get(i).getOKIPriner()*okiPrinterWatt*9.0+pPro;
      }//end of if
      if(rooms.get(i).getXerox() >0){
        pPro=rooms.get(i).getXerox()*xeroxWatt*9.0+pPro;
      }//end of if
      if(rooms.get(i).getLexmarkPrinter() >0){
        pPro=rooms.get(i).getLexmarkPrinter()*lexprinterWatt*9.0+pPro;
      }//end of if
      System.out.printf("%-20d %-30.2f", totalPrinters,pPro);
    }//end of if
    
    if(totalTV>0){
      double tPro=0;
      if(rooms.get(i).getToshibaTV() >0){
        tPro=rooms.get(i).getToshibaTV()*toshibaTVWatt*9.0+tPro;
      }//end of if
      if(rooms.get(i).getSamsungTV() >0){
        tPro=rooms.get(i).getSamsungTV()*samsungTVWatt*9.0+tPro;
      }//end of if
      if(rooms.get(i).getSharpTV() >0){
        tPro=rooms.get(i).getSharpTV()*sharpTVWatt*9.0+tPro;
      }//end of if
      System.out.printf("%-25d %-25.2f", totalTV,tPro);
    }//end of if
    
    if(totalAppliancesOther>0){
      double tA=0;
      if(rooms.get(i).getFridge() >0){
        tA=rooms.get(i).getFridge()*fridgeWatt*24.0;
      }//end of if
      if(rooms.get(i).getMiniFridge() >0){
        tA=rooms.get(i).getMiniFridge()*miniFridgeWatt*24.0;
      }//end of if
      if(rooms.get(i).getFreezerBox() >0){
        tA=rooms.get(i).getFreezerBox()*fridgeWatt*24.0;
      }//end of if
      if(rooms.get(i).getMicrowave()>0){
        tA=rooms.get(i).getMicrowave()*microwaveWatt*24.0;
      }//end of if
      if(rooms.get(i).getMultimediaCart()>0){
        tA=rooms.get(i).getMultimediaCart()*multimediaCartWatt*9.0;
      }//end of if
      if(rooms.get(i).getChromebookCart() >0){
        tA=rooms.get(i).getChromebookCart()*chromebookCartWatt*9.0;
      }//end of if
      if(rooms.get(i).getVendingMachine() >0){
        tA=rooms.get(i).getVendingMachine()*vendingMachineWatt*24.0;
      }//end of if
      System.out.printf("%-25d %-25f", totalAppliancesOther,tA);
    }//end of if
    System.out.printf("%-25.2f\n",rooms.get(r).calcTotalWatt());
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
  }//end of display
  
  /*Method used to display total watts in that room, number of tress needed and
   * CO2 emmisons
   *  @Author:Mark Arruda    @Date:
   *  @param:
   *  @return:    */
  public static void displayWatt() {
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-5s", "Room");
    System.out.printf("%-15s %-25s %-15s\n", "Total Watts","Number of Trees Needed","CO2 (In Kg)");
    System.out.printf("%-5s",rooms.get(r).getRmNumber());
    System.out.printf("%-15.2f %-25.2f %-15.2f\n", rooms.get(r).calcTotalWatt(),rooms.get(r).calcTrees(),rooms.get(r).calcEmissions());
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
  }//end of displayWatt
  
  /*
   *  @Author:Kostner Woon-Fat    @Date: Monday, June 18th, 2018
   *  @param:none
   *  @return:none
   Method Description: This method asks the user if they would like to display
   specific applications such as TVs, lights, computers, etc.    */
  public static void spefMenu() {
    Scanner in = new Scanner(System.in);
    int choice;
    do {
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      System.out.println("\t1. Display computers ");
      System.out.println("\t2. Display lights");
      System.out.println("\t3. Display projectors");
      System.out.println("\t4. Display other appliances");
      System.out.println("\t6. Display printers");
      System.out.println("\t7. Display TVs");
      //System.out.println("8. Display ")
      System.out.println("\t0. Go back");
      System.out.println("---------------------------------------------------------------------------------------------------------------------");
      while (!in.hasNextInt()) {
        System.out.println("Please enter an integer value");
        in.nextLine();
      }
      choice = in.nextInt();
      switch (choice) {
        case 1:
          displayDell();
          break;
        case 2:
          displayLight();// this one asks for model of light and checks array list for model
          break;
        case 3:
          displaySmart();
          break;
        case 4:
          displayOther();
          break;
        case 6:
          displayPrint();
          break;
        case 7:
          displayTV();
          break;
          //  case 8:
          //  spefDisplay();
          //  break;
        case 0:
          System.out.println("Going back to Sub Menu");
          break;
        default:
          System.out.println("Please enter an integer value");
          break;
      }
      
    } while (choice != 0);
  }
  /* method used to call other specific methods so everything specific is display
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void spefDisplay(){
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println(rooms.get(r).getRmNumber());
    displayDell();
    displayLight();// this one asks for model of light and checks array list for model
    displaySmart();
    displayOther();
    displayPrint();
    displayTV();    
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
  }
  
  /* method used to display specific lights
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displayLight() {
    if(rooms.get(r).getNormLights() > 0||rooms.get(r).getELights() > 0||rooms.get(r).getGO10Bulbs() > 0||rooms.get(r).getHalfCFL() > 0||rooms.get(r).getPotlights() > 0||rooms.get(r).getLargePotlights() > 0||rooms.get(r).getIncandescent() > 0){
      if(rooms.get(r).getNormLights() > 0) {
        System.out.printf("%-30s %-30s","Number of Fluorescent Light","Total Wattage of Fluorescent Light");
      }
      if(rooms.get(r).getELights() > 0) {
        System.out.printf("%-25s %-25s","Number of Emergency Lights","Total Wattage of Emergency Lights");
      }
      if(rooms.get(r).getGO10Bulbs() > 0) {
        System.out.printf("%-25s %-25s","Number of GO 10 Bulbs","Total Wattage of GO 10 Bulbs");
      }
      if(rooms.get(r).getHalfCFL() > 0){
        System.out.printf("%-25s %-25s","Number of Half CFL","Total Wattage of Half CFL");
      }
      if(rooms.get(r).getPotlights() > 0) {
        System.out.printf("%-25s %-25s","Number of Pot Lights","Total Wattage of Pot Lights");
      }
      if(rooms.get(r).getLargePotlights() > 0) {
        System.out.printf("%-25s %-25s","Number of Large Pot Lights","Total Wattage of Large Pot Lights");
      }
      if(rooms.get(r).getIncandescent() > 0) {
        System.out.printf("%-25s %-25s","Number of Incadescent","Total Wattage of Incadescent");
      }
      System.out.printf("\n");
      
      if(rooms.get(r).getNormLights() > 0) {
        System.out.printf("%-30d %-30.2f",rooms.get(r).getNormLights(),(rooms.get(r).getNormLights()*normLightWatt*9.0));
      }
      if(rooms.get(r).getELights() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getELights(),rooms.get(r).getELights()*emerLightWatt*24.0);
      }
      if(rooms.get(r).getGO10Bulbs() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getGO10Bulbs(),rooms.get(r).getGO10Bulbs()*go10bulbWatt*9.0);
      }
      if(rooms.get(r).getHalfCFL() > 0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getHalfCFL(),rooms.get(r).getHalfCFL()*halfCFLWatt*9.0);
      }
      if(rooms.get(r).getPotlights() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getPotlights(),rooms.get(r).getPotlights()*potlightWatt*24.0);
      }
      if(rooms.get(r).getLargePotlights() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getLargePotlights(),rooms.get(r).getLargePotlights()*largePotWatt*9.0);
      }
      if(rooms.get(r).getIncandescent() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getIncandescent(),rooms.get(r).getIncandescent()*incandWatt*9.0);
      }
      System.out.printf("\n");
    }
    else{
      System.out.println("No Lights Found");
    }
  }//end of displayLight
  
  /* methods used to display specific appliances and other objects
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displayOther() {
    if(rooms.get(r).getFridge() > 0||rooms.get(r).getMiniFridge() > 0||rooms.get(r).getFreezerBox() > 0||rooms.get(r).getMicrowave() > 0||rooms.get(r).getMultimediaCart() > 0||rooms.get(r).getChromebookCart() > 0||rooms.get(r).getVendingMachine() > 0){
      if(rooms.get(r).getFridge() > 0) {
        System.out.printf("%-25s %-25s","Number of Fridges","Total Wattage of Fridges");
      }
      if(rooms.get(r).getMiniFridge() > 0) {
        System.out.printf("%-25s %-25s","Number of  Mini Fridges","Total Wattage of Mini Fridges");
      }
      if(rooms.get(r).getFreezerBox() > 0) {
        System.out.printf("%-25s %-25s","Number of Freezer Box","Total Wattage of Freezer Box");
      }
      if(rooms.get(r).getMicrowave() > 0) {
        System.out.printf("%-25s %-25s","Number of Microwave","Total Wattage of Microwave");
      }
      if(rooms.get(r).getMultimediaCart() > 0) {
        System.out.printf("%-25s %-25s","Number of Multi Media Cart","Total Wattage of Multi Media Cart");
      }
      if(rooms.get(r).getChromebookCart() > 0) {
        System.out.printf("%-25s %-25s","Number of Chromebook Cart","Total Wattage of Chromebook Cart");
      }
      if(rooms.get(r).getVendingMachine() > 0) {
        System.out.printf("%-25s %-25s","Number of Vending Machines","Total Wattage of Vending Machines");
      }
      System.out.printf("\n");
      
      if(rooms.get(r).getFridge() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getFridge(),rooms.get(r).getFridge()*fridgeWatt*24.0);
      }
      if(rooms.get(r).getMiniFridge() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getMiniFridge(),rooms.get(r).getMiniFridge()*miniFridgeWatt*24.0);
      }
      if(rooms.get(r).getFreezerBox() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getFreezerBox(),rooms.get(r).getFreezerBox()*fridgeWatt*24.0);
      }
      if(rooms.get(r).getMicrowave() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getFreezerBox(),rooms.get(r).getFreezerBox()*fridgeWatt*24.0);
      }
      if(rooms.get(r).getMultimediaCart() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getMultimediaCart(),rooms.get(r).getMultimediaCart()*multimediaCartWatt*9.0);
      }
      if(rooms.get(r).getChromebookCart() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getChromebookCart(),rooms.get(r).getChromebookCart()*chromebookCartWatt*9.0);
      }
      if(rooms.get(r).getVendingMachine() > 0) {
        System.out.printf("%-25d %-25.2f",rooms.get(r).getVendingMachine(),rooms.get(r).getVendingMachine()*vendingMachineWatt*24.0);
      }
      System.out.printf("\n");
    }
    else{
      System.out.println("No Applications/Others Found");
    }
  }//end of displayOther
  
  /*method used to display specific computers
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displayDell() {
    if(rooms.get(r).getDell390()>0||rooms.get(r).getDell380()>0||rooms.get(r).getDell3020()>0||rooms.get(r).getDell3040()>0||rooms.get(r).getDell3010()>0||rooms.get(r).getHPSamsung()>0||rooms.get(r).getDell3050()>0||rooms.get(r).getMac()>0){
      if(rooms.get(r).getDell390()>0){
        System.out.printf("%-25s %-25s","Number of Dell390","Total Wattage of Dell390");
      }//end of if
      if(rooms.get(r).getDell380()>0){
        System.out.printf("%-25s %-25s","Number of Dell380","Total Wattage of Dell380");
      }//end of if
      if(rooms.get(r).getDell3020()>0){
        System.out.printf("%-25s %-25s","Number of Dell3020","Total Wattage of Dell3020");
      }//end of if
      if(rooms.get(r).getDell3040()>0){
        System.out.printf("%-25s %-25s","Number of Dell3040","Total Wattage of Dell3040");
      }//end of if
      if(rooms.get(r).getDell3010()>0){
        System.out.printf("%-25s %-25s","Number of Dell3010","Total Wattage of Dell3010");
      }//end of if
      if(rooms.get(r).getHPSamsung()>0){
        System.out.printf("%-25s %-25s","Number of HP Samsung","Total Wattage of HP Samsung");
      }//end of if
      if(rooms.get(r).getDell3050()>0){
        System.out.printf("%-25s %-25s","Number of Dell3050","Total Wattage of Dell3050");
      }//end of if
      if(rooms.get(r).getMac()>0){
        System.out.printf("%-25s %-25s","Number of Macs","Total Wattage of Macs");
      }//end of if
      System.out.printf("\n");
      
      if(rooms.get(r).getDell390()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell390(),rooms.get(r).getDell390()*dell390Watt*9.0);
      }//end of if
      if(rooms.get(r).getDell380()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell380(),rooms.get(r).getDell380()*dell380Watt*9.0);
      }//end of if
      if(rooms.get(r).getDell3020()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell3020(),rooms.get(r).getDell3020()*dell3020Watt*9.0);
      }//end of if
      if(rooms.get(r).getDell3040()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell3040(),rooms.get(r).getDell3040()*dell3040Watt*9.0);
      }//end of if
      if(rooms.get(r).getDell3010()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell3010(),rooms.get(r).getDell3010()*dell3010Watt*9.0);
      }//end of if
      if(rooms.get(r).getHPSamsung()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell3010(),rooms.get(r).getHPSamsung()*hpWatt*9.0);
      }//end of if
      if(rooms.get(r).getDell3050()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getDell3050(),rooms.get(r).getDell3050()*dell3050Watt*9.0);
      }//end of if
      if(rooms.get(r).getMac()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getMac(),rooms.get(r).getMac()*macWatt*9.0);
      }//end of if
      System.out.printf("\n");
    }
    else{
      System.out.println("No Computers Found");
    }
  }//end of displayDell
  
  /* method used to display specific smartboards and projectors
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displaySmart() {
    if(rooms.get(r).getEpsonProj()>0||rooms.get(r).getBenqProj()>0||rooms.get(r).getSmartboard()>0){
      if(rooms.get(r).getEpsonProj()>0){
        System.out.printf("%-30s %-40s","Number of Epson Projector","Total Wattage of Epson Projector");
      }//end of if
      if(rooms.get(r).getBenqProj()>0){
        System.out.printf("%-30s %-40s","Number of Benq Projector","Total Wattage of Benq Projector");
      }//end of if
      if(rooms.get(r).getSmartboard()>0){
        System.out.printf("%-30s %-40s","Number of Smartboards","Total Wattage of Smartboards");
      }//end of if
      System.out.printf("\n");
      
      if(rooms.get(r).getEpsonProj()>0){
        System.out.printf("%-30d %-40.2f",rooms.get(r).getEpsonProj(),rooms.get(r).getEpsonProj()*epsProjWatt*9.0);
      }//end of if
      if(rooms.get(r).getBenqProj()>0){
        System.out.printf("%-30d %-40.2f",rooms.get(r).getBenqProj(),rooms.get(r).getBenqProj()*benqProjWatt*9.0);
      }//end of if
      if(rooms.get(r).getSmartboard()>0){
        System.out.printf("%-30d %-40.2f",rooms.get(r).getSmartboard(),rooms.get(r).getSmartboard()*smartboardWatt*9.0);
      }//end of if
      System.out.printf("\n");
    }
    else{
      System.out.println("No Projectos/Smartboards Found");
    }
  }//end of displaySmart
  
  /* Used to display specific printers
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displayPrint() {
    if(rooms.get(r).getOKIPriner()>0||rooms.get(r).getXerox()>0||rooms.get(r).getLexmarkPrinter()>0){
      if(rooms.get(r).getOKIPriner()>0){
        System.out.printf("%-25s %-25s","Number of OKI Printers","Total Wattage of OKI Printers");
      }//end of if
      if(rooms.get(r).getXerox()>0){
        System.out.printf("%-25s %-25s","Number of Xerox","Total Wattage of Xerox Printer");
      }//end of if
      if(rooms.get(r).getLexmarkPrinter()>0){
        System.out.printf("%-25s %-25s","Number of Lexmark Printer","Total Wattage of Lexmark Printer");
      }//end of if
      System.out.printf("\n");
      
      if(rooms.get(r).getOKIPriner()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getOKIPriner(),rooms.get(r).getOKIPriner()*okiPrinterWatt*24.0);
      }//end of if
      if(rooms.get(r).getXerox()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getXerox(),rooms.get(r).getXerox()*xeroxWatt*24.0);
      }//end of if
      if(rooms.get(r).getLexmarkPrinter()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getLexmarkPrinter(),rooms.get(r).getLexmarkPrinter()*lexprinterWatt*24.0);
      }//end of if
      System.out.printf("\n");
    }
    else{
      System.out.println("No Printers Found");
    }
  }//end of displayPrint
  
  /* Usesd to dispaly specific TV
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void displayTV() {
    if(rooms.get(r).getSharpTV()>0||rooms.get(r).getSamsungTV()>0||rooms.get(r).getToshibaTV()>0){
      if(rooms.get(r).getSharpTV()>0){
        System.out.printf("%-25s %-25s","Number of Sharp TV","Total Wattage of Sharp TV");
      }//end of if
      if(rooms.get(r).getSamsungTV()>0){
        System.out.printf("%-25s %-25s","Number of Samsung TV","Total Wattage of Samsung TV");
      }//end of if
      if(rooms.get(r).getToshibaTV()>0){
        System.out.printf("%-25s %-25s","Number of Toshiba TV","Total Wattage of Toshiba TV");
      }//end of if
      System.out.printf("\n");
      
      if(rooms.get(r).getSharpTV()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getSharpTV(),rooms.get(r).getSharpTV()*sharpTVWatt*9.0);
      }//end of if
      if(rooms.get(r).getSamsungTV()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getSamsungTV(),rooms.get(r).getSamsungTV()*sharpTVWatt*9.0);
      }//end of if
      if(rooms.get(r).getToshibaTV()>0){
        System.out.printf("%-25d %-25.2f",rooms.get(r).getToshibaTV(),rooms.get(r).getToshibaTV()*toshibaTVWatt*9.0);
      }//end of if
      System.out.printf("\n");
    }
    else{
      System.out.println("No TVs Found");
    }
  }//end of displayTV
  
  /*Method sorts all the classrooms from greatest to least
   wattages and displays the top 5 classrooms (bubble sort)
   *  @Author:Mark    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */
  public static void sortRooms(){//wip
    Classroom temp=null;
    int count=0;
    // System.out.println(rooms.size());
    //   for(int i=1;i!=6;i++){
    // System.out.printf("%d.%s:%.2fW\n",i,rooms.get(i).getRmNumber(),rooms.get(i).calcTotalWatt());
    //}
    for(int out =0;out<(rooms.size()-1);out++){
      for(int in=1;in<(rooms.size());in++){
        if(rooms.get(in-1).calcTotalWatt()<rooms.get(in).calcTotalWatt()){
          temp=rooms.get(in-1);
          rooms.set(in-1,rooms.get(in));
          rooms.set(in,temp);
        }//end of if
        count++;
        //System.out.println(count);
      }//end of in
    }//end of out
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.println("\tThis displays the top 5 rooms in the school that uses the most energy in Watt hours. These can be consider problem areas in the School");
    for(int i=1;i!=6;i++){
      System.out.printf("\t%d.%s:%.2f Wh\n",i,rooms.get(i).getRmNumber(),rooms.get(i).calcTotalWatt());
    }
    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
  }//end of sortHigh
  
  /* Method used to display the total watts,cost,
   * emissions,number of tress need for the school for one day
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */  
  public static void  costDay(){
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    System.out.printf("\tThe total watts for the School for the day is %.2fKWh\n",calcWattSchool()/1000.0);
    System.out.printf("\tThe cost for the Schhool for the day is $%.2f\n",calcCostSchool());
    System.out.printf("\tThe emmisons of C02 for the School for the day is %.2fKg\n",calcEmissionsSchool());
    System.out.printf("\tThe number of tress needed for the Schhool for the day is %.2f trees\n",calcTreesSchool());
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
  }//end of costDay
  
  /*  Method used to display the total watts,cost,
   * emissions,number of tress need for the school for one day
   *  @Author:Mark Arruda    @Date:  Monday, June 18th, 2018
   *  @param:
   *  @return:    */  
  public static void costYear(){
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    System.out.printf("\tThe total watts for the School for the year is %.2fKWh\n",calcWattSchoolYear()/1000.0);
    System.out.printf("\tThe cost for the Schhool for the year is $%.2f\n",calcCostSchoolYear());
    System.out.printf("\tThe emmisons of C02 for the School for the year is %.2fKg\n",calcEmissionsSchoolYear());
    System.out.printf("\tThe number of tress needed for the year for the day is %.2f trees\n",calcTreesSchoolYear());
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
  }//end of costYear
  
  /* Displaysy the Cost of a Room for a day
   *  @Author:Mark Arruda    @Date: Monday, June 18th, 2018
   *  @param:
   *  @return:    */  
  public static void costRoom(){
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    System.out.printf("The cost for room %s for the day is $%.2f\n",rooms.get(r).getRmNumber(),rooms.get(r).calcCost());
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
  }//end of costRoom
  
  /* Displaysy the Cost of a Room for a year
   *  @Author:Mark Arruda    @Date:  Monday, June 18th, 2018
   *  @param:
   *  @return:    */  
  public static void costRoomYear(){
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
    System.out.printf("The cost for room %s for the year is $%.2f\n",rooms.get(r).getRmNumber(),rooms.get(r).calcCostYear());
    System.out.println("---------------------------------------------------------------------------------------------------------------------");
  }//end of costRoom
  
  /* Open the file and stores it all on an array list
   *  @Author:Mark Arruda    @Date:
   *  @param:
   *  @return:    */ 
  public static void openFileMethod() throws IOException{
    int x=1 ,place=0;
    String hold=null;
    File fileName = new File("info.txt");
    Scanner in = new Scanner(fileName);
    //  System.out.println("Run");
    while(in.hasNext()){
      //  System.out.println("Run Loop");
      x=1;
      rooms.add(new Classroom());
      rooms.get(place).setRmNumber(in.nextLine());
      //  System.out.println("rm num: "+rooms.get(place).getRmNumber());
      while(x==1){
        // System.out.println("Run X");
        try{
          hold=in.nextLine();
        }
        catch(NoSuchElementException e){}
        
        //  System.out.println(hold);
        if(hold.equals("Normal Lights")){
          //   System.out.println("Normal Lights");
          rooms.get(place).setNormLights(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Pot Lights")){
          rooms.get(place).setPotlights(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Workcenter 5890")){
          //      System.out.println("Workcenter 5890");
          rooms.get(place).setXerox(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Xerox")||hold.equals("Xerox Printer")||hold.equals("5955 Xerox")){
          rooms.get(place).setXerox(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Emergency Lights")){
          rooms.get(place).setELights(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("HP rp5700")){
          rooms.get(place).setHPSamsung(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Fridge")){
          rooms.get(place).setFridge(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Microwave")){
          rooms.get(place).setMicrowave(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Optiplex 390")||hold.equals("Optiplex390")||hold.equals("Dell 390")){
          rooms.get(place).setDell390(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Optiplex 3010")){
          rooms.get(place).setDell3010(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("BENQ Projector")||hold.equals("Benq Projector")){
          rooms.get(place).setBenqProj(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("iMac")){
          rooms.get(place).setMac(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Optiplex 3020")||hold.equals("Dell 3020")){
          rooms.get(place).setDell3020(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Optiplex 3040")){
          rooms.get(place).setDell3040(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("OKI B720")||hold.equals("OKI B721")||hold.equals("Oki Printer")||hold.equals("OKI Printer")){
          rooms.get(place).setOKIPriner(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Toshiba TV")||hold.equals("ToshibaTV")){
          //System.out.println("Toshiba TV");
          rooms.get(place).setToshibaTV(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Epson Projector")){
          rooms.get(place).setEpsonProj(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Samsung TV")||hold.equals("SamsungTV")){
          rooms.get(place).setSamsungTV(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Sharp TV")||hold.equals("SharpTV")){
          rooms.get(place).setSharpTV(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("5955 Xerox Printer")){
          rooms.get(place).setXerox(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("OKI Printer")){
          rooms.get(place).setOKIPriner(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Mini-Fridge")){
          rooms.get(place).setMiniFridge(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Optiplex 380")){
          rooms.get(place).setDell380(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Lexmark T630")||hold.equals("Lexmark MS8")||hold.equals("Lexmark Printer")||hold.equals("Lexmark")){
          rooms.get(place).setLexmarkPrvoider(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Vending Machine")){
          rooms.get(place).setVendingMachine(in.nextInt());
          hold=in.nextLine();
        }
        else if(hold.equals("Smart Board")||hold.equals("Smart Projector")){
          rooms.get(place).setSmartboard(in.nextInt());
          hold=in.nextLine();
        }
        else{
          //  System.out.println("run else");
          x=0;
          place++;
          // try{
          //  in.nextLine();
          //  }
          // catch(NoSuchElementException e){}
        }
      }
      
    }// end of while
  }//end of openFileMethod
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the energy consumption of the whole
   * school on a regular school day. The energy consumption is calculated in watt hours
   * @param: none     @Return: double
   */
  public static double calcWattSchool() {
    double watt = 0;
    for (int a = 0; a < rooms.size(); a++) {
      watt = watt + rooms.get(a).calcTotalWatt();
    }
    return watt;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the energy consumption of the whole
   * school throughout the school year. The energy consumption is calculated in watt hours
   * @param: none     @Return: double
   */
  public static double calcWattSchoolYear() {
    double watt = 0;
    for (int a = 0; a < rooms.size(); a++) {
      watt = watt + rooms.get(a).calcWattYear();
    }
    return watt;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates Carbon Dioxide emission equivalent of the whole
   * school on a regular school day. The emissions are calculated in kg.
   * @param: none     @Return: double
   */
  public static double calcEmissionsSchool() {
    double emissions = 0;
    for (int a = 0; a < rooms.size(); a++) {
      emissions = emissions + rooms.get(a).calcEmissions();
    }
    return emissions;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates Carbon Dioxide emission equivalent of the whole
   * school throughout the school year (10 months). The emissions are calculated in kg.
   * @param: none     @Return: double
   */
  public static double calcEmissionsSchoolYear() {
    double emissions = 0;
    for (int a = 0; a < rooms.size(); a++) {
      emissions = emissions + rooms.get(a).calcEmissionsYear();
    }
    return emissions;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates trees needed to absorb the carbon dioxide
   * emission equivalents of the whole school on a regular school day. The number of trees is
   * returned as a double value.
   * @param: none     @Return: double
   */
  public static double calcTreesSchool() {
    double tree = 0;
    for (int a = 0; a < rooms.size(); a++) {
      tree = tree + rooms.get(a).calcTrees();
    }
    return tree;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates trees needed to absorb the carbon dioxide
   * emission equivalents of the whole school throughout the school year (10 months). The
   * number of trees is returned as a double value.
   * @param: none     @Return: double
   */
  public static double calcTreesSchoolYear() {
    double tree = 0;
    for (int a = 0; a < rooms.size(); a++) {
      tree = tree + rooms.get(a).calcTreesYear();
    }
    return tree;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the cost of electricity of the whole school
   * on a regular school day. The amount is calculated in dollars and returned as a double.
   * @Param: none     @Return: double
   */
  public static double calcCostSchool() {
    double cost = 0;
    for (int a = 0; a < rooms.size(); a++) {
      cost = cost + rooms.get(a).calcCost();
    }
    return cost;
  }
  
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the cost of electricity of the whole school
   * for the whole school year (10 months). The amount is calculated in dollars and returned
   * as a double.
   * @Param: none     @Return: double
   */
  public static double calcCostSchoolYear() {
    double cost = 0;
    for (int a = 0; a < rooms.size(); a++) {
      cost = cost + rooms.get(a).calcCostYear();
    }
    return cost;
  }
  
} //end of EcoSchoolPrg



/* ************************************************************************************************
 * @Author: Jocelyn Nurtanto     @Date: Monday, June 11th, 2018
 * Class Description: This class contains all of the information for one classroom. It hasNext
 * variables that hold the number of computers, lights, printers, smartboards, and other items
 * that consume electricity in that classroom. It has methods that calculates how much energy is
 * used by the computers and lights in the class. It can also calculate the total energy consumption
 * of the classroom, the Carbon Dioxide emission equivalents and the cost of the electricity.
 * ********************************************************************************************** */
class Classroom implements WattsConstant {
  // VARIABLES
  private String rmNumber;
  // lights
  private int normLights; // normal CFL tube lights
  private int eLights; // emergency lights
  private int potlights;
  private int largePotlights;
  private int incandescent;
  private int halfCFL; // half size of a normal light
  private int go10Bulbs;
  // computers
  private int dell390;
  private int dell380;
  private int dell3020;
  private int dell3040;
  private int dell3010;
  private int dell3050;
  private int hpSamsung; // HP rp5700 with Samsung sync master 171N monitor
  private int mac; // all macbook models
  // projectors / smartboards
  private int epsonProj;
  private int benqProj;
  private int smartboard; // both old and new smartboards
  // printers / photocopiers
  private int okiPrinter; // all OKI printer models
  private int lexmarkPrinter; // all lexmark printer models
  private int xerox; // all xerox photocopier models
  // appliances
  private int fridge; // all fridge models
  private int miniFridge; // all mini fridge models
  private int freezerBox; // assume same wattage as fridge
  private int microwave; // all microwave models
  // tv's
  private int toshibaTV;
  private int samsungTV;
  private int sharpTV;
  // other
  private int multimediaCart;
  private int chromebookCart;
  private int vendingMachine; // all vending machine models
  // CONSTRUCTORS
  Classroom() {
    rmNumber = null;
    normLights = 0;
    eLights = 0;
    potlights = 0;
    largePotlights = 0;
    incandescent = 0;
    halfCFL = 0;
    go10Bulbs = 0;
    dell390 = 0;
    dell380 = 0;
    dell3020 = 0;
    dell3040 = 0;
    dell3010 = 0;
    dell3050 = 0;
    hpSamsung = 0;
    mac = 0;
    epsonProj = 0;
    benqProj = 0;
    smartboard = 0;
    okiPrinter = 0;
    lexmarkPrinter = 0;
    xerox = 0;
    fridge = 0;
    miniFridge = 0;
    freezerBox = 0;
    microwave = 0;
    toshibaTV = 0;
    samsungTV = 0;
    sharpTV = 0;
    multimediaCart = 0;
    chromebookCart = 0;
    vendingMachine = 0;
  } // default constructor
  Classroom(String rmNumber, int normLights, int eLights, int potlights, int largePotlights, int incandescent, int halfCFL, int go10Bulbs,
            int dell390, int dell380, int dell3020, int dell3040, int dell3010, int dell3050, int hpSamsung, int mac,
            int epsonProj, int benqProj, int smartboard, int okiPrinter, int lexmarkPrinter, int xerox, int fridge, int miniFridge, int freezerBox,
            int microwave, int toshibaTV, int samsungTV, int sharpTV, int multimediaCart, int chromebookCart, int vendingMachine) {
    this.rmNumber = rmNumber;
    this.normLights = normLights;
    this.eLights = eLights;
    this.potlights = potlights;
    this.largePotlights = largePotlights;
    this.incandescent = incandescent;
    this.halfCFL = halfCFL;
    this.go10Bulbs = go10Bulbs;
    this.dell390 = dell390;
    this.dell380 = dell380;
    this.dell3020 = dell3020;
    this.dell3040 = dell3040;
    this.dell3010 = dell3010;
    this.dell3050 = dell3050;
    this.hpSamsung = hpSamsung;
    this.mac = mac;
    this.epsonProj = epsonProj;
    this.benqProj = benqProj;
    this.smartboard = smartboard;
    this.okiPrinter = okiPrinter;
    this.lexmarkPrinter = lexmarkPrinter;
    this.xerox = xerox;
    this.fridge = fridge;
    this.miniFridge = miniFridge;
    this.freezerBox = freezerBox;
    this.microwave = microwave;
    this.toshibaTV = toshibaTV;
    this.samsungTV = samsungTV;
    this.sharpTV = sharpTV;
    this.multimediaCart = multimediaCart;
    this.chromebookCart = chromebookCart;
    this.vendingMachine = vendingMachine;
  } // other constructor
  // GET METHODS
  public String getRmNumber() { return rmNumber; }
  public int getNormLights() { return normLights; }
  public int getELights() { return eLights; }
  public int getPotlights() { return potlights; }
  public int getLargePotlights() { return largePotlights; }
  public int getIncandescent() { return incandescent; }
  public int getHalfCFL() { return halfCFL; }
  public int getGO10Bulbs() { return go10Bulbs; }
  public int getDell390() { return dell390; }
  public int getDell380() { return dell380; }
  public int getDell3020() { return dell3020; }
  public int getDell3040() { return dell3040; }
  public int getDell3010() { return dell3010; }
  public int getDell3050() { return dell3050; }
  public int getHPSamsung() { return hpSamsung; }
  public int getMac() { return mac; }
  public int getEpsonProj() { return epsonProj; }
  public int getBenqProj() { return benqProj; }
  public int getSmartboard() { return smartboard; }
  public int getOKIPriner() { return okiPrinter; }
  public int getLexmarkPrinter() { return lexmarkPrinter; }
  public int getXerox() { return xerox; }
  public int getFridge() { return fridge; }
  public int getMiniFridge() { return miniFridge; }
  public int getFreezerBox() { return freezerBox; }
  public int getMicrowave() { return microwave; }
  public int getToshibaTV() { return toshibaTV; }
  public int getSamsungTV() { return samsungTV; }
  public int getSharpTV() { return sharpTV; }
  public int getMultimediaCart() { return multimediaCart; }
  public int getChromebookCart() { return chromebookCart; }
  public int getVendingMachine() { return vendingMachine; }
  //SET methods
  public void setRmNumber(String R) {rmNumber=R; }
  public void setNormLights(int N) {normLights=N; }
  public void setELights(int E) {eLights=E; }
  public void setPotlights(int P) {potlights=P; }
  public void setLargePotlights(int L) {largePotlights= L; }
  public void setIncandescent(int I) {incandescent=I; }
  public void setHalfCFL(int H) {halfCFL= H; }
  public void setGO10Bulbs(int G) {go10Bulbs= G; }
  public void setDell390(int D) {dell390= D; }
  public void setDell380(int D) {dell380= D; }
  public void setDell3020(int D ) {dell3020= D; }
  public void setDell3040(int D) {dell3040= D; }
  public void setDell3010(int D ) {dell3010=D; }
  public void setDell3050(int D ) {dell3050=D; }
  public void setHPSamsung(int H) {hpSamsung=H; }
  public void setMac(int M) {  mac=M; }
  public void setEpsonProj(int E) {epsonProj=E; }
  public void setBenqProj(int B) {benqProj= B; }
  public void setSmartboard(int S) {smartboard= S; }
  public void setOKIPriner(int O) {okiPrinter=O; }
  public void setLexmarkPrvoider(int L) {lexmarkPrinter= L; }
  public void setXerox(int X) {xerox=X; }
  public void setFridge(int F) {fridge= F; }
  public void setMiniFridge(int M) {miniFridge=M; }
  public void setFreezerBox(int F) {freezerBox=F; }
  public void setMicrowave(int M) {microwave=M; }
  public void setToshibaTV(int T) {toshibaTV=T; }
  public void setSamsungTV(int S) {samsungTV=S; }
  public void setSharpTV(int S) {sharpTV=S; }
  public void setMultimediaCart(int M) {multimediaCart=M; }
  public void setChromebookCart(int C) {chromebookCart=C; }
  public void setVendingMachine(int V) {vendingMachine=V; }
  
  // methods
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the total energy consumption of all the lights in a room.
   * It returns the energy consumption as a double value in watt hours.
   * @param: none     @Return: double
   */
  public double calcTotalLights() {
    double sum = 0;
    sum = normLights*WattsConstant.normLightWatt*9.0;
    sum = sum + eLights*WattsConstant.emerLightWatt*24.0;
    sum = sum + potlights*WattsConstant.potlightWatt*24.0;
    sum = sum + largePotlights*WattsConstant.largePotWatt*9.0;
    sum = sum + incandescent*WattsConstant.incandWatt*9.0;
    sum = sum + halfCFL*WattsConstant.halfCFLWatt*9.0;
    sum = sum + go10Bulbs*WattsConstant.go10bulbWatt*9.0;
    return sum;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the total energy consumption of all the computers in
   * a room. It returns the energy consumption as a double value in watt hours.
   * @param: none     @Return: double
   */
  public double calcTotalComputers() {
    double sum = 0;
    sum = dell390*WattsConstant.dell390Watt;
    sum = sum + dell380*WattsConstant.dell390Watt;
    sum = sum + dell3020*WattsConstant.dell3020Watt;
    sum = sum + dell3040*WattsConstant.dell3040Watt;
    sum = sum + dell3010*WattsConstant.dell3010Watt;
    sum = sum + dell3050*WattsConstant.dell3050Watt;
    sum = sum + hpSamsung*WattsConstant.hpWatt;
    sum = sum + mac*WattsConstant.macWatt;
    sum = sum*9.0;
    return sum;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the total energy consumption of the whole room. It
   * returns the wattage as a double value in watt hours.
   * @param: none     @Return: double
   */
  public double calcTotalWatt() {
    double sum = 0;
    sum = epsonProj*WattsConstant.epsProjWatt*9.0;
    sum = sum + benqProj*WattsConstant.benqProjWatt*9.0;
    sum = sum + smartboard*WattsConstant.smartboardWatt*9.0;
    sum = sum + okiPrinter*WattsConstant.okiPrinterWatt*9.0;
    sum = sum + lexmarkPrinter*WattsConstant.lexprinterWatt*9.0;
    sum = sum + xerox*WattsConstant.xeroxWatt*9.0;
    sum = sum + fridge*WattsConstant.fridgeWatt*24.0;
    sum = sum + miniFridge*WattsConstant.miniFridgeWatt*24.0;
    sum = sum + freezerBox*WattsConstant.fridgeWatt*24.0;
    sum = sum + microwave*WattsConstant.microwaveWatt*24.0;
    sum = sum + toshibaTV*WattsConstant.toshibaTVWatt*9.0;
    sum = sum + samsungTV*WattsConstant.samsungTVWatt*9.0;
    sum = sum + sharpTV*WattsConstant.sharpTVWatt*9.0;
    sum = sum + multimediaCart*WattsConstant.multimediaCartWatt*9.0;
    sum = sum + chromebookCart*WattsConstant.chromebookCartWatt*9.0;
    sum = sum + vendingMachine*WattsConstant.vendingMachineWatt*24.0;
    sum = sum + calcTotalLights() + calcTotalComputers();
    return sum;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the energy consumed by a room on
   * a day in the weekend. The energy consumption is returned as a double value in watt hours.
   * @param: none     @Return: double
   */
  public double calcWattWeekend() {
    double sum = 0;
    sum = eLights*WattsConstant.emerLightWatt;
    sum = sum + potlights*WattsConstant.potlightWatt;
    sum = sum + fridge*WattsConstant.fridgeWatt;
    sum = sum + miniFridge*WattsConstant.miniFridgeWatt;
    sum = sum + freezerBox*WattsConstant.fridgeWatt;
    sum = sum + microwave*WattsConstant.microwaveWatt;
    sum = sum + vendingMachine*WattsConstant.vendingMachineWatt;
    return (sum*24.0);
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: this method calculates the amount of energy consumed by the classroom
   * in one school year (10 months). The energy consumption is returned as a double value in watt hours.
   * @param: none     @Return: double
   */
  public double calcWattYear() {
    double weekday = calcTotalWatt()*5.0;
    double weekend = calcWattWeekend()*2.0;
    return (weekday + weekend)*43.0;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the amount of carbon dioxide equivalent to the
   * energy consumed by the classroom on a regular school day. The carbon dioxide equivalents are
   * returned as a double value in kg.
   * @Param: none     @Return: double
   */
  public double calcEmissions() {
    return (calcTotalWatt()/1000.0)*WattsConstant.emissionsEquivalent;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculatea the amount of carbon dioxide that is equivalent
   * to the energy consumed by the classroom throughout the whole school year. (10 months). The
   * carbon dioxide equivalents are returned as a double value in kg.
   * @Param: none     @Return: double
   */
  public double calcEmissionsYear() {
    return (calcWattYear()/1000.0)*WattsConstant.emissionsEquivalent;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the amount of trees needed to absorb the carbon
   * dioxide gas emissions that are equivalent to the energy consumed by the classroom on a regular
   * school day.
   * @param: none     @Return: double
   */
  public double calcTrees() {
    return calcEmissions()/WattsConstant.trees;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the amount of trees needed to absorb the carbon
   * dioxide gas emissions that are equivalent to the amount of energy consumed by the classroom
   * throughout the school year (10 months)
   */
  public double calcTreesYear() {
    return calcEmissionsYear()/WattsConstant.trees;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculates the cost of the energy consumed by the room for a
   * regular school day. The cost is returned as a double value in dollars.
   * @param: none     @Return: double
   */
  public double calcCost() {
    return (calcTotalWatt()/1000.0)*WattsConstant.cost;
  }
  /*
   * @Author: Jocelyn Nurtanto     @Date: Monday, June 18th, 2018
   * method description: This method calculate the cost of the energy consumed by the Classroom
   * for a school year, including weekends.  This is based on the assumption that all school weeks
   * are 5 regulat school days and 2 weekends long. The cost is returned as a double value in dollars.
   * @Param: none     @Return: double
   */
  public double calcCostYear() {
    return (calcWattYear()/1000.0)*WattsConstant.cost;
  }
} //end of classroom class

/*@Author: Kostner Woon-Fat  @Date:  Monday, June 18th, 2018
 Description: This interface states all constants used.
 */
interface WattsConstant {
  // WATTAGES
  // computers
  final double dell390Watt = 43.2;
  final double dell380Watt = 53.2;
  final int dell3020Watt = 47;
  final int dell3040Watt = 51;
  final int dell3050Watt = 48;
  final int dell3010Watt = 45;
  final int hpWatt = 48;
  final double macWatt = 26.2;
  // lights
  final int normLightWatt = 32;
  final int emerLightWatt = 32;
  final int potlightWatt = 36;
  final int largePotWatt = 105;
  final int incandWatt = 100;
  final int halfCFLWatt = 16;
  final int go10bulbWatt = 40;
  // projectors  / smartboards /Printers
  final int epsProjWatt = 109;
  final int benqProjWatt = 262;
  final int smartboardWatt = 304;
  final double lexprinterWatt = 15.5;
  final double okiPrinterWatt = 19.7;
  final int xeroxWatt = 45;
  // appliances
  final int microwaveWatt = 114;
  final int fridgeWatt = 736;
  final int miniFridgeWatt = 136;
  // TV's
  final double toshibaTVWatt = 13.4;
  final double samsungTVWatt = 10.4;
  final double sharpTVWatt = 5.2;
  // other
  final int multimediaCartWatt = 104;
  final double chromebookCartWatt = 16.3;
  final int vendingMachineWatt = 920;
  // OTHER VALUES
  final double emissionsEquivalent = 0.18; // one kwh is equal to 0.18 kg of Carbon Dioxide emissions
  final double trees = 12.0; // one tree can absorb 12 kg of Carbon Dioxide emissions
  final double cost = 0.17; // cost of one kwh
  
} //end of interface

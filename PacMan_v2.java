// Coded By ~~Hritvik~~
import java.util.Scanner;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
class PacMan_v2 implements Runnable
{
    static Scanner scan = new Scanner(System.in);
    static Scanner scan1 = new Scanner(System.in);
    static BlockingQueue<String> user_input = new LinkedBlockingQueue<>();
    static char game_board[][] = new char[16][20];
    static int pac_row,pac_col,fruit_count,fruit_count_temp,sp_fruit_flag=0,break_flag=0;
    static int pause_flag_1=0,pause_flag_2=0,hs_flag_1=0,hs_flag_2=0,quit_flag_1=0,quit_flag_2=0,about_flag_1=0,about_flag_2=0,gamedesc_flag_1=0,gamedesc_flag_2=0;
    static int save_fruits_eat,save_moves,save_sp_fruits_eat,fruits_eat=0,moves=0,sp_fruits_eat=0;
    static char usr_input,inp_pause;
    static String usr_timed_input,save_player_name,player_name="a",strDate="____",strTime="____",save_strDate,save_strTime,strDate_user,strTime_user;
    static int gh_flag_1=0,gh_flag_2=0,gh_flag_3=0,gh_flag_4=0;
    static int temp_gh_vert,temp_gh_horz;
    static int ghost_1_current=8,ghost_2_current=15,ghost_3_current=11,ghost_4_current=3;
    public static void game_board_boundary()
    {
        int i,j;
        for(char[] Temp_board : game_board)
        {
            Arrays.fill(Temp_board, ' ');
        }
        for(i=0;i<16;i++)
        {
            game_board[i][0] = '|';
            game_board[i][19] = '|';
        }
        for(j=0;j<20;j++)
        {
            game_board[0][j] = '-';
            game_board[15][j] = '-';
        }
    }
    public static void default_walls()
    {
        game_board[2][3] = 'W';
        game_board[3][3] = 'W';
        game_board[4][3] = 'W';
        game_board[5][3] = 'W';
        game_board[3][2] = 'W';
        game_board[3][4] = 'W';
        game_board[6][8] = 'W';
        game_board[6][7] = 'W';
        game_board[7][7] = 'W';
        game_board[8][7] = 'W';
        game_board[8][8] = 'W';
        game_board[8][9] = 'W';
        game_board[8][10] = 'W';
        game_board[8][11] = 'W';
        game_board[8][12] = 'W';
        game_board[7][12] = 'W';
        game_board[6][12] = 'W';
        game_board[6][11] = 'W';
        game_board[7][2] = 'W';
        game_board[7][3] = 'W';
        game_board[8][3] = 'W';
        game_board[9][3] = 'W';
        game_board[9][2] = 'W';
        game_board[11][2] = 'W';
        game_board[11][3] = 'W';
        game_board[12][2] = 'W';
        game_board[12][3] = 'W';
        game_board[2][6] = 'W';
        game_board[3][6] = 'W';
        game_board[4][6] = 'W';
        game_board[8][5] = 'W';
        game_board[9][5] = 'W';
        game_board[10][5] = 'W';
        game_board[12][5] = 'W';
        game_board[13][5] = 'W';
        game_board[13][6] = 'W';
        game_board[4][8] = 'W';
        game_board[3][8] = 'W';
        game_board[2][8] = 'W';
        game_board[2][9] = 'W';
        game_board[2][10] = 'W';
        game_board[2][11] = 'W';
        game_board[3][11] = 'W';
        game_board[4][11] = 'W';
        game_board[10][8] = 'W';
        game_board[11][8] = 'W';
        game_board[12][8] = 'W';
        game_board[12][9] = 'W';
        game_board[12][10] = 'W';
        game_board[12][11] = 'W';
        game_board[12][12] = 'W';
        game_board[11][12] = 'W';
        game_board[10][12] = 'W';
        game_board[10][10] = 'W';
        game_board[2][14] = 'W';
        game_board[2][15] = 'W';
        game_board[2][16] = 'W';
        game_board[3][15] = 'W';
        game_board[4][15] = 'W';
        game_board[6][14] = 'W';
        game_board[7][14] = 'W';
        game_board[8][14] = 'W';
        game_board[9][14] = 'W';
        game_board[10][14] = 'W';
        game_board[14][12] = 'W';
        game_board[14][13] = 'W';
        game_board[14][14] = 'W';
        game_board[2][18] = 'W';
        game_board[3][18] = 'W';
        game_board[6][16] = 'W';
        game_board[7][16] = 'W';
        game_board[8][16] = 'W';
        game_board[7][17] = 'W';
        game_board[9][18] = 'W';
        game_board[10][18] = 'W';
        game_board[11][16] = 'W';
        game_board[12][14] = 'W';
        game_board[12][15] = 'W';
        game_board[12][16] = 'W';
        game_board[12][17] = 'W';
        game_board[13][16] = 'W';
        game_board[14][18] = 'W';
        game_board[2][13] = 'W';
        game_board[14][2] = 'W';
        game_board[14][3] = 'W';
        game_board[1][11] = 'W';
        game_board[5][1] = 'W';
    }
    public static void startup_pacman_v2()
    {
        int i;
        clr_scr();
        System.out.println("\t\t\t     ....................................................................................................\n\n");
        System.out.println("\t\t\t\t\t\t\t       WELCOME TO PAC-MAN v2.67");
        System.out.println("\n\t\t\t\t\t\t\t\t Coded By ~~Hritvik~~");
        System.out.println("\n\n\t\t\t\t\t\t    <<<<<     WARNING!! Read It Carefully!!     >>>>>");
        System.out.println("\n\n\t\t\t      This Game Uses The Concept Of Threading & Threads Used In Game Is Given Highest Priority By Default");
        System.out.println("\t\t\t    So, It Is Recommended To Close All Background and Currently Running/Executing Programs Before Proceeding");
        System.out.println("\t\t\t\t\t        Otherwise Game Does'nt Respond Properly Or Game Crashes.......");
        System.out.println("\t\t\t\t\t             And Maximize The Window Size For Proper Visuals..");
        try
        {
            Thread.sleep(1500);
            System.out.print("\n\n\n\tChecking/Loading Save Game Files");
            for(i=0;i<6;i++)
            {
                System.out.print(".");
                Thread.sleep(800);
            }
            Thread.sleep(2000);
        }
        catch(Exception ee){}
        try
        {
            FileInputStream fin1 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_fruits_eat.dat");
            FileInputStream fin2 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_moves.dat");
            FileInputStream fin3 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_sp_fruits_eat.dat");
            BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_player_main.dat"));
            BufferedReader bf2 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_date.dat"));
            BufferedReader bf3 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_time.dat"));
            fin1.close();
            fin2.close();
            fin3.close();
            bf1.close();
            bf2.close();
            bf3.close();
            System.out.println("\n\n\t  Save Game Loaded Successfully");
        }
        catch(Exception e)
        {
            System.out.println("\n\n\t  Save Game Not Found!!!!");
        }
        System.out.print("\n\n\n\t\t\t\t\t\t      Redirecting To Main Game In 8 Second(s)");
        try
        {
            for(i=8;i>0;i--)
            {
                System.out.print(".");
                Thread.sleep(1000);
            }
        }
        catch(Exception e){}
    }
    public static void instructions()
    {
        System.out.println ("\n\t\t.................................................................................................................................\n");
        System.out.println ("\t\t\t\t\t\t\t\t    Welcome To Pac-Man v2");
        System.out.println ("\t\t\t\t\t\t\t\t      ~By Hritvik Maini\n\n");
        System.out.println ("\t\t\t\t\t\t\t       *** MUST READ BEFORE PLAYING!! ***\n");
        System.out.println ("\t\t\t\t\t\t\t\t\t   Controls");
        System.out.println ("\t\t\t\t\t\t\t\t\t ------------");
        System.out.println ("\n\t\t\t\tThis Program Is A Pac-Man Game (CMD Version). There Are 4 Main Keys For Controlling Pac-Man's Movement:");
        System.out.println ("\n\t\t\t\t\t\t\t\t\t  W - Up");
        System.out.println ("\t\t\t\t\t\t\t\t\t  A - Left");
        System.out.println ("\t\t\t\t\t\t\t\t\t  S - Down");
        System.out.println ("\t\t\t\t\t\t\t\t\t  D - Right");
        System.out.println ("\t\t\t\t\t\t\t\t\t  P - Pause Menu");
        System.out.println ("\n\t\t\t\t\t\t\t   Other Options Are Available In Pause Menu");
        System.out.println ("\n\t\t\t\t\tPac-Man Was Originally Launched In 1980. So, Almost Everyone Knows About This Game.");
        System.out.println ("\t\t\t\t    In This, Player Have To Move Pac-Man (#) In All Directions & Eating Fruits (@) In Their Way.");
        System.out.println ("\t\t\tThere Are Also GHOSTS (^) In Game With The Ability To Kill Pac-Man. So, While Moving Make Sure They Don't Touch Pac-Man.");
        System.out.println ("\t\t     There Is A Little Addition From My Side That There Is A SPECIAL FRUIT ($) It Only Appears When Pac-Man Eats At Least 10 FRUITS.");
        System.out.println ("\t\t\t\t\t\t\t   More Info About This Is Available In Pause Menu.\n");
        System.out.println ("\t\t\t\tDue To A Small Bug Player Has To Enter The Same Choise Twice In Pause Menu. This Will Be Fixed Soon!!!\n");
        System.out.println ("\t\t\t\t\t\t\t\t\t So, Let's PLAY!!!\n");
    }
    public static void getname()
    {
        System.out.print("\n\n\t   Enter Player Name: ");
        player_name = scan1.nextLine();
        System.out.println();
    }
    public static void greet()
    {
        int i;
        try
        {
            for(i=5;i>0;i--)
            {
                System.out.println("\t\t\t     ....................................................................................................\n");
                System.out.println("\n\t\t\t\t\t\t\t\t      Hi " + player_name + "! Let's Start In..\n");
                System.out.println("\t\t\t\t\t\t\t\t\t         "+i);
                Thread.sleep(1000);
                clr_scr();
            }
        }
        catch(Exception e){}
    }
    public static void save_high_state()
    {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        strDate = dateFormat.format(date);
        strTime = timeFormat.format(date);
        try
        {
            FileOutputStream fout1 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_fruits_eat.dat");
            FileOutputStream fout2 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_moves.dat");
            FileOutputStream fout3 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_sp_fruits_eat.dat");
            BufferedWriter bf1 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_player_main.dat"));
            BufferedWriter bf2 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_date.dat"));
            BufferedWriter bf3 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_time.dat"));
            if(fruits_eat>save_fruits_eat || sp_fruits_eat>save_sp_fruits_eat)
            {
                fout1.write(fruits_eat);
                fout2.write(moves);
                fout3.write(sp_fruits_eat);
                bf1.write(player_name);
                bf2.write(strDate);
                bf3.write(strTime);
            }
            else
            {
                fout1.write(save_fruits_eat);
                fout2.write(save_moves);
                fout3.write(save_sp_fruits_eat);
                bf1.write(save_player_name);
                bf2.write(save_strDate);
                bf3.write(save_strTime);
            }
            fout1.close();
            fout2.close();
            fout3.close();
            bf1.close();
            bf2.close();
            bf3.close();
        }
        catch(Exception e)
        {
            clr_scr();
            System.out.println("\t\t\t     ....................................................................................................\n");
            System.out.println("\t\t\t\t\t\t   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t\t\t        Critical Error Occured, Pls Restart Game!!!");
            System.out.println("\t\t\t\t\t\t   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            System.out.println("\t\t\t\tExiting Game......\n\n");
            System.exit(0);
        }
    }
    public static void pause_disp_default()
    {
        System.out.println("\t\t\t     ....................................................................................................\n");
        System.out.println("\t\t\t\t\t\t\t\t\tPause Menu");
        System.out.println("\t\t\t\t\t\t\t\t     ----------------\n");
    }
    public synchronized static void pause_menu()
    {
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t   R - Resume\n");
        System.out.println("\t\t\t\t   Q - Quit\n");
        System.out.println("\t\t\t\t   H - Show High Score\n");
        System.out.println("\t\t\t\t   G - About Game\n");
        System.out.println("\t\t\t\t   C - Save Current Progress\n");
        System.out.println("\t\t\t\t   L - Load Saved Progress\n");
        System.out.println("\t\t\t\t   V - Game Description");
        System.out.println("\n\t\t\t     ....................................................................................................");
        if(pause_flag_1==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t    Invalid Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            pause_flag_1 = 0;
        }
        if(pause_flag_2==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t       No Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            pause_flag_2 = 0;
        }
        System.out.print("\n\t\t\t\t     Enter Your Choise: ");
        
        try
        {
            inp_pause = scan1.nextLine().charAt(0);
            if(inp_pause=='r' || inp_pause=='R')
            {
                pm_game_resume();
            }
            else if(inp_pause=='q' || inp_pause=='Q')
            {
                pm_quit_screen();
            }
            else if(inp_pause=='h' || inp_pause=='H')
            {
                pm_high_score();
            }
            else if(inp_pause=='g' || inp_pause=='G')
            {
                pm_about_game();
            }
            else if(inp_pause=='c' || inp_pause=='C')
            {
                pm_save_progress();
            }
            else if(inp_pause=='l' || inp_pause=='L')
            {
                pm_load_progress();
            }
            else if(inp_pause=='v' || inp_pause=='V')
            {
                pm_game_desc();
            }
            else
            {
                pause_flag_1 = 1;
                pause_menu();
            }
        }
        catch(Exception e)
        {
            pause_flag_2 = 1;
            pause_menu();
        }
    }
    public static void pm_game_resume()
    {
        int i;
        clr_scr();
        try
        {
            for(i=4;i>0;i--)
            {
                print_game_board();
                System.out.print("\n\t\t\t\t\t\t\t\t    Resuming Game In ");
                System.out.print(i);
                Thread.sleep(1000);
                clr_scr();
            }
            ghost_movement();
        }
        catch(Exception e){}
    }
    public static void pm_quit_screen()
    {
        char quit_inp;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t\tQuit Game");
        System.out.println("\t\t\t\t\t\t\t\t      ..............");
        System.out.println("\n\n\t\t\t\t<<<<< WARNING: Make Sure You Have Saved Your Current Progress else It Will Not Be Saved!!! >>>>>");
        System.out.println("\n\t\t\t     ....................................................................................................");
        if(quit_flag_1==1)
        {
            System.out.println("\n\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t      Invalid Input, Try Again!!");
            System.out.println("\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            quit_flag_1 = 0;
        }
        if(quit_flag_2==1)
        {
            System.out.println("\n\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t         No Input, Try Again!!");
            System.out.println("\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            quit_flag_2 = 0;
        }
        System.out.print("\n\t\t\t\t   Are You Really Want To Exit (Y/N): ");
        try
        {
            quit_inp = scan1.nextLine().charAt(0);
            if(quit_inp=='Y' || quit_inp=='y')
            {
                clr_scr();
                System.out.println("\n\t\t\t     ....................................................................................................");
                System.out.println("\n\t\t\t\t\t\t\t\t   Thanks For Playing....");
                about_game_screen();
                System.exit(0);
            }
            else if(quit_inp=='N' || quit_inp=='n')
            {
                pause_menu();
            }
            else
            {
                quit_flag_1 = 1;
                pm_quit_screen();
            }
        }
        catch(Exception e)
        {
            quit_flag_2 = 1;
            pm_quit_screen();
        }
    }
    public static void pm_high_score()
    {
        char hs_inp;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t\tHigh Score");
        System.out.println("\t\t\t\t\t\t\t\t      ..............");
        System.out.println("\n\n\t\t\t\t\t\t\t      Last Saved On "+save_strDate+" At "+save_strTime);
        System.out.println("\n\n\t\t\t\t\tMade By "+save_player_name+" With:");
        System.out.println("\n\t\t\t\t\t      Total Moves (#): "+save_moves);
        System.out.println("\t\t\t\t\t      Total Fruits Eaten (@): "+save_fruits_eat);
        System.out.println("\t\t\t\t\t      Total Special Fruits Eaten ($): "+save_sp_fruits_eat);
        System.out.println("\n\t\t\t     ....................................................................................................");
        System.out.println("\n\t\t\t\t     P - Pause Menu");
        if(hs_flag_1==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t    Invalid Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            hs_flag_1 = 0;
        }
        if(hs_flag_2==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t       No Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            hs_flag_2 = 0;
        }
        System.out.print("\n\t\t\t\t   Enter Your Choise: ");
        try
        {
            hs_inp = scan1.nextLine().charAt(0);
            if(hs_inp=='p' || hs_inp=='P')
            {
                pause_menu();
            }
            else
            {
                hs_flag_1 = 1;
                pm_high_score();
            }
        }
        catch(Exception e)
        {
            hs_flag_2 = 1;
            pm_high_score();
        }
    }
    public static void pm_about_game()
    {
        char about_inp;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t      About Pac-Man");
        System.out.println("\t\t\t\t\t\t\t\t    .................");
        System.out.println("\n\n\t\t\t\t   About Game:");
        System.out.println("\t\t\t\t ---------------");
        System.out.println("\n\t\t\t\t\t     Game Name:               Pac-Man v2");
        System.out.println("\t\t\t\t\t     Game Version:            2.67");
        System.out.println("\t\t\t\t\t     GUI:                     Command Prompt v10");
        System.out.println("\t\t\t\t\t     Game Build Language:     Java (JDK v14.0.1)");
        System.out.println("\t\t\t\t\t     Editor Used:             Visual Studio Code (v1.46.1)");
        System.out.println("\t\t\t\t\t     Multi-Threading Used:    Yes");
        System.out.println("\n\t\t\t\t   Game Design:");
        System.out.println("\t\t\t\t ---------------");
        System.out.println("\n\t\t\t\t\t     Coded By:               ~Hritvik~");
        System.out.println("\t\t\t\t\t     Board Designed By:       My Mom");
        System.out.println("\n\t\t\t\t    Bugs:");
        System.out.println("\t\t\t\t  ---------");
        System.out.println("\n\t\t\t\t\t     Resolved:                5");
        System.out.println("\t\t\t\t\t     Not Resolved Yet:        1 - (Due To Threading, Player Need To Input The Same");
        System.out.println("\t\t\t\t\t\t\t\t\t   Option Twice In Pause Menu Choises)");
        System.out.println("\n\t\t\t     ....................................................................................................");
        System.out.println("\n\t\t\t\t     P - Pause Menu");
        if(about_flag_1==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t    Invalid Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            about_flag_1 = 0;
        }
        if(about_flag_2==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t       No Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            about_flag_2 = 0;
        }
        System.out.print("\n\t\t\t\t   Enter Your Choise: ");
        try
        {
            about_inp = scan1.nextLine().charAt(0);
            if(about_inp=='p' || about_inp=='P')
            {
                pause_menu();
            }
            else
            {
                about_flag_1 = 1;
                pm_about_game();
            }
        }
        catch(Exception e)
        {
            about_flag_2 = 1;
            pm_about_game();
        }
    }
    public static void pm_save_progress()
    {
        int i;
        String validate_player;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t   Save Current Progress");
        System.out.println("\t\t\t\t\t\t\t\t...........................");
        System.out.print("\n\n\t\t\t\t   Enter Current Player Name: ");
        try
        {
            validate_player = scan1.nextLine();
            if(validate_player.equals(player_name))
            {
                System.out.println("\n\t\t\t\t      Player Verified!!!");
                System.out.print("\n\t\t\t\t   Saving Current Progress");
                for(i=0;i<6;i++)
                {
                    System.out.print(".");
                    Thread.sleep(800);
                }
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                strDate_user = dateFormat.format(date);
                strTime_user = timeFormat.format(date);
                FileOutputStream fout1 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_fruits_eat.dat");
                FileOutputStream fout2 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_moves.dat");
                FileOutputStream fout3 = new FileOutputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_sp_fruits_eat.dat");
                BufferedWriter bf1 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_player_main.dat"));
                BufferedWriter bf2 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_usrsv_date.dat"));
                BufferedWriter bf3 = new BufferedWriter(new FileWriter("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_usrsv_time.dat"));
                fout1.write(fruits_eat);
                fout2.write(moves);
                fout3.write(sp_fruits_eat);
                bf1.write(player_name);
                bf2.write(strDate_user);
                bf3.write(strTime_user);
                fout1.close();
                fout2.close();
                fout3.close();
                bf1.close();
                bf2.close();
                bf3.close();
                System.out.print("\n\n\t\t\t\t  Current Progress Saves Successfully!!");
                System.out.println("\n\n\t\t\t     ....................................................................................................");
                System.out.println("\n\t\t\t\t   Returning To Pause Menu...");
                Thread.sleep(1500);
                pause_menu();
            }
            else
            {
                System.out.println("\n\t\t\t     ....................................................................................................");
                System.out.println("\n\t\t\t\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("\t\t\t\t       Error: Player Names Mismatch, Returning To Pause Menu!!!");
                System.out.println("\t\t\t\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Thread.sleep(2500);
                pause_menu();
            }
        }
        catch(Exception e){}
    }
    public static void pm_load_progress()
    {
        String temp_date,temp_time,load_scan,temp_player;
        int i;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t    Load Saved Progress");
        System.out.println("\t\t\t\t\t\t\t\t .........................");
        System.out.print("\n\t\t\t\t Checking Saved Game Files");
        try
        {
            for(i=0;i<6;i++)
            {
                System.out.print(".");
                Thread.sleep(800);
            }
            BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_player_main.dat"));
            BufferedReader bf2 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_usrsv_date.dat"));
            BufferedReader bf3 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_usrsv_time.dat"));
            System.out.println("\n\n\t\t\t\t  Save Game Files Found!!");
            temp_date = bf2.readLine();
            temp_time = bf3.readLine();
            System.out.println("\n\n\t\t\t\t\t\t\t    Last Saved On "+temp_date+" At "+temp_time);
            bf2.close();
            bf3.close();
            temp_player = bf1.readLine();
            bf1.close();
            System.out.print("\n\t\t\t\t Enter Saved Player Name: ");
            load_scan = scan1.nextLine();
            if(load_scan.equals(temp_player))
            {
                System.out.println("\n\t\t\t\t   Player Verified!!!");
                System.out.print("\n\t\t\t\tFetching Data From Save Files");
                for(i=0;i<6;i++)
                {
                    System.out.print(".");
                    Thread.sleep(800);
                }
                FileInputStream fout1 = new FileInputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_fruits_eat.dat");
                FileInputStream fout2 = new FileInputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_moves.dat");
                FileInputStream fout3 = new FileInputStream("C:\\Games\\Pac-Man_v2\\User_Saves\\.pac-man_v2.67_save_user_sp_fruits_eat.dat");
                fruits_eat = fout1.read();
                moves = fout2.read();
                sp_fruits_eat = fout3.read();
                player_name = temp_player;
                fout1.close();
                fout2.close();
                fout3.close();
                System.out.println("\n\n\t\t\t\t Game Loaded Successfully!!!");
                System.out.println("\n\t\t\t     ....................................................................................................");
                System.out.println("\n\t\t\t\t   Returning To Pause Menu...");
                Thread.sleep(1500);
                pause_menu();
            }
            else
            {
                System.out.println("\n\t\t\t     ....................................................................................................");
                System.out.println("\n\t\t\t\t   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("\t\t\t\t      Error: Player Names Mismatch, Returning To Pause Menu!!!");
                System.out.println("\t\t\t\t   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Thread.sleep(2500);
                pause_menu();
            }     
        }
        catch(Exception e)
        {
            System.out.println("\n\t\t\t     ....................................................................................................");
            System.out.println("\n\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t    Error: No Saved Progress Found, Returning To Pause Menu!!!");
            System.out.println("\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try
            {
                Thread.sleep(2500);
            }
            catch(Exception ex){}
            pause_menu();
        }
    }
    public static void pm_game_desc()
    {
        char gamedesc_input;
        clr_scr();
        pause_disp_default();
        System.out.println("\t\t\t\t\t\t\t\t     Game Description");
        System.out.println("\t\t\t\t\t\t\t\t  ......................");
        System.out.println("\n\t\t\t\tGhost (^)");
        System.out.println("\t\t\t      -------------");
        System.out.println("\n\t\t\t\t\t   Ghosts Are Constantly Moving During Game Session & They Can Move Only In Their-"); 
        System.out.println("\t\t\t\t\t   Defined Path. They Have The Ability To Kill Pac-Man & They Do Not Eat Any Fruits.");
        System.out.println("\n\t\t\t\tPac-Man (#)");
        System.out.println("\t\t\t      --------------");
        System.out.println("\n\t\t\t\t\t   Pac-Man Can Move In All 4 Directions & It Can Eat Fruits As Well As Special Fruits-"); 
        System.out.println("\t\t\t\t\t   Pac-Man Can Be Eaten By Ghosts If It Comes In Their Way. Every Time When Game Starts-");
        System.out.println("\t\t\t\t\t   Pac-Man Will Be Spawned At Different Location On Board.");
        System.out.println("\n\t\t\t\tFruits (@)");
        System.out.println("\t\t\t      --------------");
        System.out.println("\n\t\t\t\t\t   Pac-Man Can Eat Fruits Which Comes In Their Way. Total No Of Fruits Eaten Will Be-"); 
        System.out.println("\t\t\t\t\t   Stored In External Files So That Player Can Reuse That Score & Continue Their Game.");
        System.out.println("\n\t\t\t     Special Fruits ($)");
        System.out.println("\t\t\t   ----------------------");
        System.out.println("\n\t\t\t\t\t   Pac-Man Can Also Eat Special Fruit Which Only Appears After Player Eats At Least 10-"); 
        System.out.println("\t\t\t\t\t   Fruits. Number Of Special Fruits Are Seperately Counted & Stored Like Normal Fruits.");
        System.out.println("\n\t\t\t     ....................................................................................................");
        System.out.println("\n\t\t\t\t     P - Pause Menu");
        if(gamedesc_flag_1==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t    Invalid Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            gamedesc_flag_1 = 0;
        }
        if(gamedesc_flag_2==1)
        {
            System.out.println("\n\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t\t\t\t       No Input, Try Again!!");
            System.out.println("\t\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            gamedesc_flag_2 = 0;
        }
        System.out.print("\n\t\t\t\t   Enter Your Choise: ");
        try
        {
            gamedesc_input = scan1.nextLine().charAt(0);
            if(gamedesc_input=='p' || gamedesc_input=='P')
            {
                pause_menu();
            }
            else
            {
                gamedesc_flag_1 = 1;
                pm_game_desc();
            }
        }
        catch(Exception e)
        {
            gamedesc_flag_2 = 1;
            pm_game_desc();
        }
    }
    public static void default_ghosts()
    {
        game_board[8][1] = '^';
        game_board[5][15] = '^';
        game_board[11][13] = '^';
        game_board[1][3] = '^';
    }
    public static void ghost_movement_vertical_down(int i, int j)
    {
        temp_gh_vert = i+1;
        game_board[i+1][j] = game_board[i][j];
        game_board[i][j] = ' ';
        game_board[i+2][j] = game_board[i+1][j];
    }
    public static void ghost_movement_vertical_up(int i, int j)
    {
        temp_gh_vert = i-1;
        game_board[i-1][j] = game_board[i][j];
        game_board[i][j] = ' ';
        game_board[i-2][j] = game_board[i-1][j];
    }
    public static void ghost_movement_horizontal_fwd(int i, int j)
    {
        temp_gh_horz = j+1;
        game_board[i][j+1] = game_board[i][j];
        game_board[i][j] = ' ';
        game_board[i][j+2] = game_board[i][j+1];
    }
    public static void ghost_movement_horizontal_bwd(int i, int j)
    {
        temp_gh_horz = j-1;
        game_board[i][j-1] = game_board[i][j];
        game_board[i][j] = ' ';
        game_board[i][j-2] = game_board[i][j-1];
    }
    public static void ghost_movement()
    {
        while(true)
        {
            clr_scr();
            print_game_board();
            if(break_flag==1)
            {
                break_flag = 0;
                break;
            }
            try
            {
                System.out.println("\n\t----------------------------------------------");
                System.out.println("\n\t      Total Moves: "+moves);
                System.out.println("\t      Total Fruits Eaten: "+fruits_eat);
                System.out.println("\t      Total Special Fruits Eaten: "+sp_fruits_eat);
                System.out.print("\n\t      Enter Your Choise: ");
                usr_timed_input = user_input.poll(100, TimeUnit.MILLISECONDS);
                if(usr_timed_input=="p" || usr_timed_input=="P")
                {
                    break_flag = 1;
                    continue;
                }
                else if(usr_timed_input==null)
                {
                    if(gh_flag_1==0)
                    {
                        if(game_board[ghost_1_current+1][1]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        else if(game_board[ghost_1_current+1][1]=='-')
                        {
                            gh_flag_1 = 1;
                            continue;
                        }
                        else if(game_board[ghost_1_current+1][1]=='@' || game_board[ghost_1_current+1][1]=='$')
                        {
                            if(game_board[ghost_1_current+1][1]=='@' && game_board[ghost_1_current+2][1]=='-')
                            {
                                gh_flag_1 = 1;
                                continue;
                            }
                            else if(game_board[ghost_1_current+1][1]=='@')
                            {
                                ghost_movement_vertical_down(ghost_1_current,1);
                                game_board[temp_gh_vert][1] = '@';
                            }
                            else
                            {
                                ghost_movement_vertical_down(ghost_1_current,1);
                                game_board[temp_gh_vert][1] = '$';
                            }
                            ghost_1_current+=2;
                        }
                        else
                        {
                            game_board[ghost_1_current+1][1] = game_board[ghost_1_current][1];
                            game_board[ghost_1_current][1] = ' ';
                            ghost_1_current++;
                        }
                    }
                    else if(gh_flag_1==1)
                    {
                        if(game_board[ghost_1_current-1][1]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[ghost_1_current-1][1]=='W')
                        {
                            gh_flag_1=0;
                            continue;
                        }
                        else if(game_board[ghost_1_current-1][1]=='@' || game_board[ghost_1_current-1][1]=='$')
                        {
                            if(game_board[ghost_1_current-1][1]=='@' && game_board[ghost_1_current-2][1]=='W')
                            {
                                gh_flag_1 = 0;
                                continue;
                            }
                            else if(game_board[ghost_1_current-1][1]=='@')
                            {
                                ghost_movement_vertical_up(ghost_1_current,1);
                                game_board[temp_gh_vert][1] = '@';
                            }
                            else
                            {
                                ghost_movement_vertical_up(ghost_1_current,1);
                                game_board[temp_gh_vert][1] = '$';
                            }
                            ghost_1_current-=2;
                        }
                        else
                        {
                            game_board[ghost_1_current-1][1] = game_board[ghost_1_current][1];
                            game_board[ghost_1_current][1] = ' ';
                            ghost_1_current--;
                        }
                    }
                    if(gh_flag_2==0)
                    {
                        if(game_board[5][ghost_2_current-1]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[5][ghost_2_current-1]=='W' || game_board[5][ghost_2_current-1]=='^')
                        {
                            gh_flag_2=1;
                            continue;
                        }
                        else if(game_board[5][ghost_2_current-1]=='@' || game_board[5][ghost_2_current-1]=='$')
                        {
                            if(game_board[5][ghost_2_current-1]=='@' && game_board[5][ghost_2_current-2]=='W')
                            {
                                gh_flag_2 = 1;
                                continue;
                            }
                            else if(game_board[5][ghost_2_current-1]=='@')
                            {
                                ghost_movement_horizontal_bwd(5,ghost_2_current);
                                game_board[5][temp_gh_horz] = '@';
                            }
                            else
                            {
                                ghost_movement_horizontal_bwd(5,ghost_2_current);
                                game_board[5][temp_gh_horz] = '$';
                            }
                            ghost_2_current-=2;
                        }
                        else
                        {
                            game_board[5][ghost_2_current-1] = game_board[5][ghost_2_current];
                            game_board[5][ghost_2_current] = ' ';
                            ghost_2_current--;
                        }
                    }
                    else if(gh_flag_2==1)
                    {
                        if(game_board[5][ghost_2_current+1]=='#')
                        {
                            gameover_highscore_decide();
                        }   
                        if(game_board[5][ghost_2_current+1]=='|' || game_board[5][ghost_2_current+1]=='^')
                        {
                            gh_flag_2=0;
                            continue;
                        }
                        else if(game_board[5][ghost_2_current+1]=='@' || game_board[5][ghost_2_current+1]=='$')
                        {
                            if(game_board[5][ghost_2_current+1]=='@' && game_board[5][ghost_2_current+2]=='|')
                            {
                                gh_flag_2 = 0;
                                continue;
                            }
                            else if(game_board[5][ghost_2_current+1]=='@')
                            {
                                ghost_movement_horizontal_fwd(5,ghost_2_current);
                                game_board[5][temp_gh_horz] = '@';
                            }
                            else
                            {
                                ghost_movement_horizontal_fwd(5,ghost_2_current);
                                game_board[5][temp_gh_horz] = '$';
                            }
                            ghost_2_current+=2;
                        }
                        else
                        {
                            game_board[5][ghost_2_current+1] = game_board[5][ghost_2_current];
                            game_board[5][ghost_2_current] = ' ';
                            ghost_2_current++;
                        }
                    }
                    if(gh_flag_3==0)
                    {
                        if(game_board[ghost_3_current-1][13]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[ghost_3_current-1][13]=='W' || game_board[ghost_3_current-1][13]=='^')
                        {
                            gh_flag_3=1;
                            continue;
                        }
                        else if(game_board[ghost_3_current-1][13]=='@' || game_board[ghost_3_current-1][13]=='$')
                        {
                            if(game_board[ghost_3_current-1][13]=='@' && game_board[ghost_3_current-2][13]=='W')
                            {
                                gh_flag_3 = 1;
                                continue;
                            }
                            else if(game_board[ghost_3_current-1][13]=='@')
                            {
                                ghost_movement_vertical_up(ghost_3_current,13);
                                game_board[temp_gh_vert][13] = '@';
                            }
                            else
                            {
                                ghost_movement_vertical_up(ghost_3_current,13);
                                game_board[temp_gh_vert][13] = '$';
                            }
                            ghost_3_current-=2;
                        }
                        else
                        {
                            game_board[ghost_3_current-1][13] = game_board[ghost_3_current][13];
                            game_board[ghost_3_current][13] = ' ';
                            ghost_3_current--;
                        }
                    }
                    else if(gh_flag_3==1)
                    {
                        if(game_board[ghost_3_current+1][13]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[ghost_3_current+1][13]=='W' || game_board[ghost_3_current+1][13]=='^')
                        {
                            gh_flag_3=0;
                            continue;
                        }
                        else if(game_board[ghost_3_current+1][13]=='@' || game_board[ghost_3_current+1][13]=='$')
                        {
                            if(game_board[ghost_3_current+1][13]=='@' && game_board[ghost_3_current+2][13]=='W')
                            {
                                gh_flag_3 = 0;
                                continue;
                            }
                            else if(game_board[ghost_3_current+1][13]=='@')
                            {
                                ghost_movement_vertical_down(ghost_3_current,13);
                                game_board[temp_gh_vert][13] = '@';
                            }
                            else
                            {
                                ghost_movement_vertical_down(ghost_3_current,13);
                                game_board[temp_gh_vert][13] = '$';
                            }
                            ghost_3_current+=2;
                        }
                        else
                        {
                            game_board[ghost_3_current+1][13] = game_board[ghost_3_current][13];
                            game_board[ghost_3_current][13] = ' ';
                            ghost_3_current++;
                        }
                    }
                    if(gh_flag_4==0)
                    {
                        if(game_board[1][ghost_4_current+1]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[1][ghost_4_current+1]=='W')
                        {
                            gh_flag_4 = 1;
                            continue;
                        }
                        else if(game_board[1][ghost_4_current+1]=='@' || game_board[1][ghost_4_current+1]=='$')
                        {
                            if(game_board[1][ghost_4_current+1]=='@' && game_board[1][ghost_4_current+2]=='W')
                            {
                                gh_flag_4 = 1;
                                continue;
                            }
                            else if(game_board[1][ghost_4_current+1]=='@')
                            {
                                ghost_movement_horizontal_fwd(1,ghost_4_current);
                                game_board[1][temp_gh_horz] = '@';
                            }
                            else
                            {
                                ghost_movement_horizontal_fwd(1,ghost_4_current);
                                game_board[1][temp_gh_horz] = '$';
                            }
                            ghost_4_current+=2;
                        }
                        else
                        {
                            game_board[1][ghost_4_current+1] = game_board[1][ghost_4_current];
                            game_board[1][ghost_4_current] = ' ';
                            ghost_4_current++;
                        }
                    }
                    else if(gh_flag_4==1)
                    {
                        if(game_board[1][ghost_4_current-1]=='#')
                        {
                            gameover_highscore_decide();
                        }
                        if(game_board[1][ghost_4_current-1]=='|')
                        {
                            gh_flag_4 = 0;
                            continue;
                        }
                        else if(game_board[1][ghost_4_current-1]=='@' || game_board[1][ghost_4_current-1]=='$')
                        {
                            if(game_board[1][ghost_4_current-1]=='@' && game_board[1][ghost_4_current-2]=='|')
                            {
                                gh_flag_4 = 0;
                                continue;
                            }
                            else if(game_board[1][ghost_4_current-1]=='@')
                            {
                                ghost_movement_horizontal_bwd(1,ghost_4_current);
                                game_board[1][temp_gh_horz] = '@';
                            }
                            else
                            {
                                ghost_movement_horizontal_bwd(1,ghost_4_current);
                                game_board[1][temp_gh_horz] = '$';
                            }
                            ghost_4_current-=2;
                        }
                        else
                        {
                            game_board[1][ghost_4_current-1] = game_board[1][ghost_4_current];
                            game_board[1][ghost_4_current] = ' ';
                            ghost_4_current--;
                        }
                    }
                }
                else
                {
                    pacman_v2_main(usr_timed_input.charAt(0));
                }
            }
            catch(Exception e)
            {
                clr_scr();
                print_game_board();
                System.out.println("\n\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("\t         No Input, Try Again!!!");
                System.out.println("\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                ghost_movement();
            }
        }
    }
    public static void print_game_board()
    {
        int i,j;
        System.out.println("\n\t\t\t     ....................................................................................................\n");
        for(i=0;i<16;i++)
        {
            System.out.print("\t\t\t\t\t");
            for(j=0;j<20;j++)
            {
                System.out.print(game_board[i][j] + "   ");
            }
            System.out.println("\n");
        }
        System.out.println("\t\t\t     ....................................................................................................");
    }
    public static void gameover_highscore_decide()
    {
        clr_scr();
        print_game_board();
        save_high_state();
        if(fruits_eat>save_fruits_eat || sp_fruits_eat>save_sp_fruits_eat)
        {
            high_score_screen();
        }
        else
        {
            game_over_screen();
        }
        score_screen();
        System.exit(0);
    }
    public static void game_over_screen()
    {
        System.out.println("\n\t\t\t\t\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t\t\t       GAME OVER!!!");
        System.out.println("\t\t\t\t\t\t\t\t  ~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static void high_score_screen()
    {
        System.out.println("\n\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t\t\t     Congratulations " + player_name + " On Making A New HIGH SCORE!!!");
        System.out.println("\t\t\t\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");  
    }
    public static void about_game_screen()
    {
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPac-Man v2.67");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCoded By: ~Hritvik Maini");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSubmitted To: Manik Sir (CU-HP)");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tLanguage Used: JAVA (JDK v14.0.1)");
        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tEditor Used: VS Code (v1.46.1)\n\n\n");
    }
    public static void score_screen()
    {
        System.out.println("\n\t\t\t\t\t\t\t    ...................................\n");
        System.out.println("\t\t\t\t\t\t\t\t    Player Name: " + player_name + "\n");
        System.out.println("\t\t\t\t\t\t\t\t     Total Moves: " + moves + "");
        System.out.println("\t\t\t\t\t\t\t\t  Total Fruits Eaten: " + fruits_eat + "");
        System.out.println("\t\t\t\t\t\t\t       Total Special Fruits Eaten: " + sp_fruits_eat);
        System.out.println("\t\t\t\t\t\t\t    ...................................\n");
        System.out.println("\n\t\t\t\t\t\t\t\t   Thanks For Playing....");
        about_game_screen();
    }
    public static int random_row()
    {
        int range,min,max,rand;
        min = 1;
        max = 15;
        range = max-min+1;
        rand = (int)(Math.random()*range)+min;
        return rand;
    }
    public static int random_col()
    {
        int range,min,max,rand;
        min = 1;
        max = 19;
        range = max-min+1;
        rand = (int)(Math.random()*range)+min;
        return rand;
    }
    public static int random()
    {
        int range,min,max,rand;
        min = 1;
        max = 5;
        range = max-min+1;
        rand = (int)(Math.random()*range)+min;
        return rand;
    }
    public static void clr_scr()
    {   
	    try
        {	
		    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception E)
		{
			System.out.println(E);
		}
    }
    public static void pacman_init()
    {
        pac_row = random_row();
        pac_col = random_col();
        if(game_board[pac_row][pac_col]=='-' || game_board[pac_row][pac_col]=='|' || game_board[pac_row][pac_col]=='@' || game_board[pac_row][pac_col]=='W' || game_board[pac_row][pac_col]=='^')
        {
            pacman_init();
        }
        else
        {
            pacman_spawn_area();
        }
    }
    public static void pacman_spawn_area()
    {
        int rand = random();
        if(rand==1)
        {
            pac_row = 11;
            pac_col = 11;
            game_board[11][11] = '#';
        }
        else if(rand==2)
        {
            pac_row = 1;
            pac_col = 17;
            game_board[1][17] = '#';
        }
        else if(rand==3)
        {
            pac_row = 3;
            pac_col = 9;
            game_board[3][9] = '#';
        }
        else if(rand==4)
        {
            pac_row = 8;
            pac_col = 17;
            game_board[8][17] = '#';
        }
        else
        {
            pac_row = 7;
            pac_col = 8;
            game_board[7][8] = '#';
        }
    }
    public static void fruits_init()
    {
        int fruit_row,fruit_col;
        fruit_row = random_row();
        fruit_col = random_col();
        if(game_board[fruit_row][fruit_col]=='@' || game_board[fruit_row][fruit_col]=='-' || game_board[fruit_row][fruit_col]=='|' || game_board[fruit_row][fruit_col]=='$' || game_board[fruit_row][fruit_col]=='W' || game_board[fruit_row][fruit_col]=='^' || game_board[fruit_row][fruit_col]=='#')
        {
            fruits_init();
        }
        else if(game_board[fruit_row][fruit_col]=='@' && game_board[fruit_row-1][fruit_col]=='@' || game_board[fruit_row+1][fruit_col]=='@' || game_board[fruit_row][fruit_col-1]=='@' || game_board[fruit_row][fruit_col+1]=='@')
        {
            fruits_init();
        }
        else if(fruit_count<5)
        {
            game_board[fruit_row][fruit_col] = '@';
        }
    }
    public static void special_fruit_init()
    {
        int sp_fruit_row,sp_fruit_col;
        sp_fruit_row = random_row();
        sp_fruit_col = random_col();
        if(game_board[sp_fruit_row][sp_fruit_col]=='@' || game_board[sp_fruit_row][sp_fruit_col]=='-' || game_board[sp_fruit_row][sp_fruit_col]=='|' || game_board[sp_fruit_row][sp_fruit_col]=='W' || game_board[sp_fruit_row][sp_fruit_col]=='^')
        {
            special_fruit_init();
        }
        else if(fruit_count_temp>=10  && sp_fruit_flag==0)
        {
            game_board[sp_fruit_row][sp_fruit_col] = '$';
            sp_fruit_flag=1;
        }
    }
    public static void pacman_move_w()
    {
        game_board[pac_row-1][pac_col] = game_board[pac_row][pac_col];
        game_board[pac_row][pac_col] = ' ';
        pac_row--;
    }
    public static void pacman_move_s()
    {
        game_board[pac_row+1][pac_col] = game_board[pac_row][pac_col];
        game_board[pac_row][pac_col] = ' ';
        pac_row++;
    }
    public static void pacman_move_a()
    {
        game_board[pac_row][pac_col-1] = game_board[pac_row][pac_col];
        game_board[pac_row][pac_col] = ' ';
        pac_col--;
    }
    public static void pacman_move_d()
    {
        game_board[pac_row][pac_col+1] = game_board[pac_row][pac_col];
        game_board[pac_row][pac_col] = ' ';
        pac_col++;
    }
    public static void invalid_screen()
    {
        System.out.println("\n\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t        Invalid Move, Try Again!!!");
        System.out.println("\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static void pacman_v2_main(char input)
    {
        usr_input = input;
        if(usr_input=='w' || usr_input=='W')
        {
            if(game_board[pac_row-1][pac_col]=='-' || game_board[pac_row-1][pac_col]=='|' || game_board[pac_row-1][pac_col]=='W')
            {
                clr_scr();
                print_game_board();
                invalid_screen();
                ghost_movement();
            }
            else if(game_board[pac_row-1][pac_col]=='@')
            {
                pacman_move_w();
                fruit_count--;
                fruits_init();
                fruit_count_temp++;
                fruits_eat++;
                moves++;
            }
            else if(game_board[pac_row-1][pac_col]=='$')
            {
                pacman_move_w();
                sp_fruit_flag=0;
                fruit_count_temp=0;
                special_fruit_init();
                sp_fruits_eat++;
                moves++;
            }
            else
            {
                pacman_move_w();
                moves++;
            }
        }
        else if(usr_input=='s' || usr_input=='S')
        {
            if(game_board[pac_row+1][pac_col]=='-' || game_board[pac_row+1][pac_col]=='|' || game_board[pac_row+1][pac_col]=='W')
            {
                clr_scr();
                print_game_board();
                invalid_screen();
                ghost_movement();
            }
            else if(game_board[pac_row+1][pac_col]=='@')
            {
                pacman_move_s();
                fruit_count--;
                fruits_init();
                fruit_count_temp++;
                fruits_eat++;
                moves++;
            }
            else if(game_board[pac_row+1][pac_col]=='$')
            {
                pacman_move_s();
                sp_fruit_flag=0;
                fruit_count_temp=0;
                special_fruit_init();
                sp_fruits_eat++;
                moves++;
            }
            else
            {
                pacman_move_s();
                moves++;
            }
        }
        else if(usr_input=='a' || usr_input=='A')
        {
            if(game_board[pac_row][pac_col-1]=='-' || game_board[pac_row][pac_col-1]=='|' || game_board[pac_row][pac_col-1]=='W')
            {
                clr_scr();
                print_game_board();
                invalid_screen();
                ghost_movement();
            }
            else if(game_board[pac_row][pac_col-1]=='@')
            {
                pacman_move_a();
                fruit_count--;
                fruits_init();
                fruit_count_temp++;
                fruits_eat++;
                moves++;
            }
            else if(game_board[pac_row][pac_col-1]=='$')
            {
                pacman_move_a();
                sp_fruit_flag=0;
                fruit_count_temp=0;
                special_fruit_init();
                sp_fruits_eat++;
                moves++;
            }
            else
            {
                pacman_move_a();
                moves++;
            }
        }
        else if(usr_input=='d' || usr_input=='D')
        {
            if(game_board[pac_row][pac_col+1]=='-' || game_board[pac_row][pac_col+1]=='|' || game_board[pac_row][pac_col+1]=='W')
            {
                clr_scr();
                print_game_board();
                invalid_screen();
                ghost_movement();
            }
            else if(game_board[pac_row][pac_col+1]=='@')
            {
                pacman_move_d();
                fruit_count--;
                fruits_init();
                fruit_count_temp++;
                fruits_eat++;
                moves++;
            }
            else if(game_board[pac_row][pac_col+1]=='$')
            {
                pacman_move_d();
                sp_fruit_flag=0;
                fruit_count_temp=0;
                special_fruit_init();
                sp_fruits_eat++;
                moves++;
            }
            else
            {
                pacman_move_d();
                moves++;
            }
        }
        else if(usr_input=='p' || usr_input=='P')
        {
            break_flag = 1;
            pause_menu();
        }
        else
        {
            clr_scr();
            print_game_board();
            System.out.println("\n\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t        Invalid Input, Try Again!!!");
            System.out.println("\t    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            ghost_movement();
        }
        clr_scr();
        special_fruit_init();
        print_game_board();
        ghost_movement();
    }
    public void run()
    {
        while(true)
        {
            user_input.add(scan.nextLine());
        }
    }
    public static void main(String args[])
    {
        startup_pacman_v2();
        clr_scr();
        instructions();
        getname();
        clr_scr();
        greet();
        try
        {
            FileInputStream fin1 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_fruits_eat.dat");
            FileInputStream fin2 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_moves.dat");
            FileInputStream fin3 = new FileInputStream("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_sp_fruits_eat.dat");
            BufferedReader bf1 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_save_player_main.dat"));
            BufferedReader bf2 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_date.dat"));
            BufferedReader bf3 = new BufferedReader(new FileReader("C:\\Games\\Pac-Man_v2\\Saves\\.pac-man_v2.67_high_time.dat"));
            save_fruits_eat = fin1.read();
            save_moves = fin2.read();
            save_sp_fruits_eat = fin3.read();
            save_player_name = bf1.readLine();
            save_strDate = bf2.readLine();
            save_strTime = bf3.readLine();
            fin1.close();
            fin2.close();
            fin3.close();
            bf1.close();
            bf2.close();
            bf3.close();
        }
        catch(Exception ex)
        {
            save_fruits_eat = 0;
            save_moves = 0;
            save_sp_fruits_eat = 0;
            save_player_name = "____";
            save_strDate = "____";
            save_strTime = "____";
        }
        PacMan_v2 pac_v2 = new PacMan_v2();
        Thread t1 = new Thread(pac_v2);
        clr_scr();
        game_board_boundary();
        default_walls();
        default_ghosts();
        pacman_init();
        while(fruit_count<5)
        {
            fruits_init();
            fruit_count++;
        }
        print_game_board();
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.setDaemon(true);
        t1.start();
        ghost_movement();
        pause_menu();
    }
}
// Coded By ~~Hritvik~~
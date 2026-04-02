
/**
 * Sotm Testing
 */

import java.util.Scanner;
public class SOTM
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Robot position x:");
        double rdx=sc.nextDouble();
        System.out.print("Robot position y:");
        double rdy=sc.nextDouble();
        System.out.print("Robot velocity x:");
        double rvx=sc.nextDouble();
        System.out.print("Robot velocity y:");
        double rvy=sc.nextDouble();
        
        double nv = sotm(rdx, rdy, rvx, rvy)[0];
        double thetaT = sotm(rdx, rdy, rvx, rvy)[1];
        double rd = sotm(rdx, rdy, rvx, rvy)[2];
        
        System.out.printf("Robot Distance: %.2f%n",rd);
        System.out.printf("Final Shoot Speed: %.2f%n",nv);
        System.out.printf("Final Shoot Turret Angle: %.2f%n",thetaT*180/Math.PI);
        
        double shooterRPM = shoot(nv, rd)[0];
        double thetaH = shoot(nv, rd)[1];
        
        System.out.printf("Final Shooter RPM: %.2f%n", shooterRPM);
        System.out.printf("Final Hood Angle: %.2f%n", thetaH*180/Math.PI);        
    }
    // Returns Shooter Speed and Angle
    public static double[] sotm(double rdx, double rdy, double rvx, double rvy){        
        double rd=Math.sqrt(rdx*rdx+rdy*rdy);
        double t=timeCali(rd);
        
        //System.out.println(t);
        
        // Ball Velocity Vector
        double bvx = rdx/t;
        double bvy = rdy/t;
        
        // Summed Velocity Vector
        double nvx = bvx-rvx;
        double nvy = bvy-rvy;
        
        // Determine Final Velocity Vector
        double nv = Math.sqrt(Math.pow(nvx, 2)+Math.pow(nvy, 2));
        double theta2 = Math.atan(nvy/nvx);
        
        return new double[] {nv, theta2, rd};
    }
    public static double[] shoot(double nv, double rd){
        // Calculate Shooter Angle and RPM
        double thetaH1=angleCali(rd); //Determine hood angle
            //System.out.println("ThetaH1: "+thetaH1*180/Math.PI);
        double bv1=speedCali(rd);
            //System.out.println("bv1: "+bv1);
        double bvz = bv1*Math.sin(thetaH1);
            //System.out.println("bvz: "+bvz);
        double bvxy1 = bv1*Math.cos(thetaH1);
            //System.out.println("bvxy1: "+bvxy1);
        
        double thetaH2=Math.atan(bvz/nv);
        double shooterRPM=rpmCali(Math.sqrt(Math.pow(bvz, 2)+Math.pow(nv, 2)));
        
        return new double[] {shooterRPM, thetaH2};
    }
    public static double timeCali(double dist){
        // Determine Shooter Time in s based on distance - FAKE
        return 1.0+0.1*dist;
    }
    public static double angleCali(double dist){
        // Determine Hood Angle in radians based on distance - FAKE
        return Math.PI/2.0-dist*0.05;   
    }
    public static double speedCali(double rd){
        // Determine Shooter Speed in m/s based on d - FAKE
         return rd*0.95;   
    }
    public static double rpmCali(double nv){
        // Determine Shooter Speed in rpm based on m/s - FAKE
         return nv*300.0;   
    }
}


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
        
        double nv = sotm(new double[] {rdx, rdy}, new double[] {rvx, rvy})[0];
        double thetaT = sotm(new double[] {rdx, rdy}, new double[] {rvx, rvy})[1];
        double rd = sotm(new double[] {rdx, rdy}, new double[] {rvx, rvy})[2];
        
        System.out.printf("Final Shoot Speed: %.2f%n",nv);
        System.out.printf("Final Shoot Turret Angle: %.2f%n",thetaT*180/Math.PI);
        
        double shooterRPM = shoot(nv, thetaT, rd)[0];
        double thetaH = shoot(nv, thetaT, rd)[1];
        
        System.out.printf("Final Shooter RPM: %.2f%n", shooterRPM);
        System.out.printf("Final Hood Angle: %.2f%n", thetaH*180/Math.PI);        
    }
    // Returns Shooter Speed and Angle
    public static double[] sotm(double[] robotvel, double[] robotdisp){
        // Robot Displacement Vector
        double rdx = robotdisp[0];
        double rdy = robotdisp[1];
        
        double rd=Math.sqrt(rdx*rdx+rdy*rdy);
        double t=timeCali(rd);
        
        // Robot Velocity Vector
        double rvx = robotvel[0];
        double rvy = robotvel[1];
        
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
    public static double[] shoot(double nv, double theta2, double rd){
        // Calculate Shooter Angle and RPM
        double thetaH=angleCali(rd);
        double shooterRPM=speedCali(nv/Math.cos(thetaH));
        
        return new double[] {shooterRPM, thetaH};
    }
    public static double timeCali(double dist){
        // Determine Shooter Time in s based on distance - FAKE
        return 1+0.1*dist;
    }
    public static double angleCali(double dist){
        // Determine Hood Angle in radians based on distance
        return Math.PI/2-dist*0.2;   
    }
    public static double speedCali(double nv){
        // Determine Shooter Speed in rpm based on speed
         return nv*300;   
    }
}
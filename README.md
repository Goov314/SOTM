# SOTM
Shoot On the Move ideas for FRC

## Given Variables
* Robot Position X -> rdx
* Robot Position Y -> rdy

* Robot Velocity X -> rvx
* Robot Velocity Y -> rvy

* Time Calibration Curve
* Hood Angle Calibration Curve
* Distance to RPM Calibration Curve

## Other Variables
* Robot Position Vector -> rd
* Time -> t

* Ball Velocity X -> bvx
* Ball Velocity Y -> bvy
* Ball Velocity Z -> bvz
* Ball Velocity XY -> bvxy

* Original Ball Velocity

* Summed Velocity X -> nvx
* Summed Velocity Y -> nvy
* Summed Velocity Vector Magnitude -> nv
* Summed Velocity Vector Angle -> thetaT

* Hood Angle -> thetaH

## Math
rd = sqrt(rdx^2+rdy^2)

t = timeCali(rd)

bvx = rdx/t

bvy = rdy/t

nvx = bvx-rvx

nvy = bvy-rvy

nv = sqrt(nvx^2+nvy^2)

thetaT = atan(nvy/nvx)

thetaH1 = angleCali(rd)

bv1 = speedCali(rd)

bvz = sin(thetaH1)

bvxy = nv/bv*cos(thetaH1)

thetaH2 = atan(bvz/nv)

shooterRPM = speedCali(rd)*nv/bv

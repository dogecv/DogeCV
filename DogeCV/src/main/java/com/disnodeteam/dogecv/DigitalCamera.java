package com.disnodeteam.dogecv;

import org.opencv.core.Point;
import org.opencv.core.Size;

public class DigitalCamera {
    //all calculations are done in millimeters
    public double focalLength = 0;
    public CameraSensor cameraSensor;
    public double x = 0, y = 0, z = 0;
    public double pitch = 0, roll = 0, yaw = 0;

    public double yAng = 0, zAng = 0;

    public final double EFFECTIVE_STONE_HEIGHT = 4.35;

    public class CameraSensor{
        public final double width,
                            height;
        public CameraSensor(double widthInches, double heightInches){
            this.width = widthInches;
            this.height = heightInches;
        }
        public CameraSensor(double pixelSize, double numPixelsX, double numPixelsY){
            this.width = pixelSize * numPixelsX;
            this.height = pixelSize * numPixelsY;
        }

    }

    //TODO: Add magnification to calculations
    public DigitalCamera(double focalLength, double pixelSize, double resolutionX, double resolutionY) {
        this.focalLength = focalLength;
        cameraSensor = new CameraSensor(pixelSize, resolutionX, resolutionY);
    }

    public DigitalCamera(double focalLength, double width, double height) {
        this.focalLength = focalLength;
        cameraSensor = new CameraSensor(width, height);
    }

    public void setLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setLocation(DigitalCamera aCamera) {
        setLocation(aCamera.x, aCamera.y, aCamera.z);
    }

    public void setOrientation(double pitch, double roll, double yaw) {
        this.pitch = pitch;
        this.roll = roll;
        this.yaw = yaw;
    }

    public void setOrientation(DigitalCamera aCamera) {
        setOrientation(aCamera.pitch, aCamera.yaw, aCamera.roll);
    }

    public void setLocationAndOrientation(DigitalCamera aCamera){
        setLocation(aCamera);
        setOrientation(aCamera);
    }

//Given a point on an image, returns the coordinates on the field relative to the robot
    public Point getObjectLocation(Point pointOnImage, Size imageSize, double objectHeight){
    
        //translates the coordinate system so that the center of the image has pixel coordinates (0,0)
        Point temp = new Point(pointOnImage.x, pointOnImage.y);
        double width = Math.max(imageSize.height, imageSize.width);
        double height = Math.min(imageSize.height, imageSize.width);
        temp.x -= width / 2;
        temp.y -= height / 2;
        
        //rotates the coordinate system so that the x axis does not intersect the ground plane
        double tempX = temp.x, tempY = temp.y;
        temp.x = Math.cos(-roll) * tempX - Math.sin(-roll) * tempY;
        temp.y = Math.sin(-roll) * tempX + Math.cos(-roll) * tempY;
        
        //converts the given pixel coordinates to angles 
        double vertAng = temp.y / height * horizontalAngleOfView() - pitch;
        double horiAng = temp.x / width * verticalAngleOfView() - yaw;
        
        //raises the ground plane to 1/2 of the height of the object being detected and finds the point of intersection
        double newY = (z - objectHeight/ 2) / Math.tan(-vertAng);
        double newX = newY * Math.tan(horiAng);
        newY *= -1;
        return new Point(newX-x, newY-y);
    }

    public void updateLocation(double xChange, double yChange, double zChange){
        x += xChange;
        y += yChange;
        z += zChange;
    }

    public void updateOrientation(double pitchChange, double yChange, double zChange){
        pitch += pitchChange;
        yAng += yChange;
        zAng += zChange;
    }

    public double horizontalAngleOfView(double widthRatio){
        return 2 * Math.atan2(cameraSensor.width * widthRatio, 2 * focalLength);
    }

    public double horizontalAngleOfView(){
        return horizontalAngleOfView(1);
    }
    public double verticalAngleOfView(double heightRatio){
        return 2 * Math.atan2(cameraSensor.height * heightRatio, 2 * focalLength);
    }

    public double verticalAngleOfView(){
        return verticalAngleOfView(1);
    }
}

<h1 align = "center">  Concurrency by Yusif Mukhtarov </h1> 



## General Information

The main purpose of the program is to convert the grayscale pixels into color for each of the given sized boxes in single-processing and multi-processing modes. The main idea behind the method of converting grayscale into color consists of two parts.

In the first part, we should pay attention to the fact that almost all grayscale images have the same red, green, and blue colors (for example - (171, 172, 175)). By taking the average value of those three colors, we can create a new color by modifying each channel. If the average color value is less than 85 (I divided 255 by 3 to obtain 3 ranges of intensity), we divide the red value by three and keep the other two values unchanged to decrease intensity. This adjustment is necessary because in grayscale, small values of colors (less than 85, for example [65, 63, 62]) indicate low density. The determination of these values was made through experiments in which I manipulated the color channel values.

Following the same logic, if the average value of colors falls between 85 and 175, we divide the green value by three and keep the others unchanged. If the average value is above 170 (indicating high density), we divide the blue value by three and keep the others unchanged.

There are three functions in this program. The first function, "change_to_colorful," is created to take in X and Y ranges and change the color of pixels within those ranges. The other two functions process the image in single-processing and multi-processing modes.



## How to run
```
Input: <program> [image] [size] [mode] {options}

Arguments:  
      image           The name of the file of any format (jpg, png, jpeg).  
      size            The size of the square for the averaging.  
      mode            'S' - single threaded and 'M' - multi threaded.
Examples:
      java Main.java garden.png 10 M
      java Main.java dog.jpg 20 S
```






### Image in grayscale:  
![image](https://github.com/Yusif-bit/concurrency-assignment/blob/main/garden.png)  


### The image after the processing:  
![image](https://github.com/Yusif-bit/concurrency-assignment/blob/main/result.png)  

### Image in grayscale:  
![image](https://github.com/Yusif-bit/concurrency-assignment/blob/main/dog.jpg)  


### The image after the processing:  
![image](https://github.com/Yusif-bit/concurrency-assignment/blob/main/result.jpg) 

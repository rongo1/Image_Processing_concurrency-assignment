<h1 align = "center">  Concurrency by Yusif Mukhtarov </h1> 



## General Information

The main purpose of the programm is to convert the grayscale pixels into color for each of the given sized boxes in single-processing and multi-processing modes. The main idea standing behid of method converting grayscale into color consists of two parts. In the first part we should pay attention to the fact that almost all the grayscale images have the same red, green and blue colors (for example - (171, 172, 175)). So, by taking the average value of those three we can make new color by changing each channel. If the average color value is less that 85 (I divided 255 by 3 to get 3 ranges of intensity) we divide the red value by three and keep other 2 values unchanged to decrease intensity, because in grayscale the small values of colors (less than 85, for example [65, 63, 62]) shows low density (it was determined by experiments, in which I was changing the values of color channels). Based on same logic if the average value of colors are between 85 and 175, we divide green by 3 and keep others unchanged, and if it is more than 170 (high density) we divide blue by 3 and keep others unchanged. 

There are 3 functions. The first one "change_to_colorful" is created to take in X and Y ranges and change the color of pixels in those ranges. Other two functions are processing image in single-processing and multi-processing modes.



## How to run
```
Input: <program> [image] [size] [mode] {options}

Arguments:  
      image           The name of the file of any format (jpg, png, jpeg).  
      size            The size of the square for the averaging.  
      mode            'S' - single threaded and 'M' - multi threaded.  

```






### Here is an example of an image before processing:  
![image](https://github.com/Yusif-bit/concurrency-assignment/blob/main/dog.jpg)  


### The image after processing:  
![image](https://github.com/Yusif-bit/concurrency-assignmen/blob/main/result.jpg)  

 







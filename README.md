 学习java GUI
 
 gwtool -- java简单桌面图形小工具
 

 # 打包工具
    1、java -> 打包jar， 编译器即可（idea 、eclipse）
        
    2、jar -> 打包exe （运行已经安装jre的环境）， 推荐exe4j工具
    3、exe安装版（未安装jre的环境），推荐 Inno Setup Compiler程序
       官方下载地址如下：
       http://www.jrsoftware.org/isdl.php
       
    详细教程参考：https://www.jianshu.com/p/3d472e75634b?utm_campaign
    
# 小坑
    Q.打包exe后程序运行失败
    A. 1、java的 jre和打包的jar放在同个目录， jre用相对路径
       2、勾上exe4j 工具打包配置的 Allow JREs with a beta version number (支持jdk 小版本如1.8.0.7)
       3、勾上exe4j 配置支持64位
       
    Q.程序运行中文乱码
    A. -Dfile.encoding=utf-8   
    
 开源界面参考:
 https://www.open-open.com/project/tag/swing-waiguan.html?page=1
 https://github.com/JackJiang2011/Swing9patch
    
    

    
 
 
 
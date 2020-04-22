###JVM参数调优
`-Xms`

`-Xmx`

`-Xss`
####JVM常用命令
    ·jps = java ps     jps -l   jstack 进程号 查看堆栈
####jvm参数类型
   #### 标配参数
   - java -version
   - java -help
   #### x参数
   - -Xint 解释执行
   - -Xcomp 
   - -Xminxed
   ####xx参数（重点）
   - Boolean类型
      - *公式 -XX:+或者-某个属性值*
      - *+ 标识开启*
      - *- 标题是关闭*
   - kv设值类型
      - *公式 属性key=属性值value*
      - *列子1-XX:MetaspaceSize=128m*
      - *列子2-XX:MaxTenuringThreshold=15*
   - jps命令
      - jinfo -flag 具体参数 java进程编号
      - jinfo -flags 多个参数 java进程编号
   - 查看jvm默认值
      - *java -XX:+PrintFlagsInitial--jvm初始化参数*
      - *java -XX:+PrintFlagsFinal--查看最终参数*
      - *java -XX:+PrintCommandLineFlags--查看最终参数*
   - =号:=的区别(加冒号的是修改过的)
      - uintx MetaspaceSize = 21807104
   ####常用参数
   - 栈空间管运行堆空间管存储
   - -Xms 初始大小内存默认物理内存的1/64
   - -Xmx 最大的分配内存，默认为物理内存1/4
   - -Xss 设置单个线程的堆大小
   - -Xmn 设置年轻代大小
###GC示意图
   ![avatar](/images/1586334881.jpg)
- -XXSurvivorRatio 设置新生代中的eden和s0/s1区的比列
  默认8:1:1
- -XX:NewRatio 配置年轻代和老年代的比列 默认老2:新1
- -XX:MaxTenuringThreshold 设置垃圾最大的年龄 默认15
 
 -- 工具安装
https://www.jianshu.com/p/f61a4f1f4405
      
      
  
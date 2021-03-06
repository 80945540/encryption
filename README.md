# JAVA加密系列（零）-加密运算合集与性能分析
## 加密介绍
### 单向加密
单向加密又称为不可逆加密算法，其密钥是由加密散列函数生成的。单向散列函数一般用于产生消息摘要，密钥加密等，常见的有：
+ 1、MD5（Message Digest Algorithm 5）：是RSA数据安全公司开发的一种单向散列算法，非可逆，相同的明文产生相同的密文；
+ 2、SHA（Secure Hash Algorithm）：可以对任意长度的数据运算生成一个160位的数值。其变种由SHA192，SHA256，SHA384等；
+ 3、HMAC(Hash Message Authentication Code，散列消息鉴别码)

算法特征
+ 输入一样，输出必然相同；
+ 雪崩效应，输入的微小改变，将会引起结果的巨大变化；
+ 定长输出，无论原始数据多大，结果大小都是相同的；
+ 不可逆，无法根据特征码还原原来的数据；

### 对称加密
采用单钥密码的加密方法，同一个密钥可以同时用来加密和解密，这种加密方法称为对称加密，也称为单密钥加密。常用的单向加密算法：
+ AES（Advanced Encryption Standard）：高级加密标准，是下一代的加密算法标准，速度快，安全级别高，支持128、192、256、512位密钥的加密；
+ DES（Data Encryption Standard）：数据加密标准，速度较快，适用于加密大量数据的场合；
+ 3DES（Triple DES）：是基于DES，对一块数据用三个不同的密钥进行三次加密，强度更高；
+ Blowfish 等等 包括后面会提到的位运算加密

算法特征
+ 加密方和解密方使用同一个密钥；
+ 加密解密的速度比较快，适合数据比较长时的使用；
+ 密钥传输的过程不安全，且容易被破解，密钥管理也比较麻烦；

### 非对称加密
非对称密钥加密也称为公钥加密，由一对公钥和私钥组成。公钥是从私钥提取出来的。可以用公钥加密，再用私钥解密，这种情形一般用于公钥加密；也可以用私钥加密，用公钥解密，常用于数字签名，因此非对称加密的主要功能就是加密和数字签名。
+ RSA 第一个能同时用于加密和数字签名的算法，也易于理解和操作。RSA是被研究得最广泛的公钥算法，从提出到现今的三十多年里，经历了各种攻击的考验，逐渐为人们接受，截止2017年被普遍认为是最优秀的公钥方案之一。
 + DSA Elgamal和Schnorr数字签名的一个变种，DSA数字签名优于Elgamal数字签名的地方在于它的签名长度较短，并且某些可以破解Elgamal方案的攻击不适用DSA数字签名
 + ECC 椭圆曲线密码编码学，是目前已知的公钥体制中，对每比特所提供加密强度最高的一种体制。在软件注册保护方面起到很大的作用，一般的序列号通常由该算法产生。 
 + Elgamal 其基础是DiffieˉHellman密钥交换算法，应对中间人攻击时非常脆弱。
 
 算法特征
 + 密钥对，公钥(public key)和私钥(secret key)
 + 可以完成数字签名和数字鉴别。
 + 公钥密码是对大数进行操作，计算量特别浩大，速度远比不上私钥密码体制。（私钥加密快，公钥解密慢）
 
 ## 性能比较
 ![图片](https://github.com/80945540/encryption/blob/master/src/img/Image1.png)
 
 
  ## 详情文章
   + [JAVA加密系列（一）- Base64与单向加密算法MD5、SHA、HMAC](https://github.com/80945540/encryption/blob/master/src/util/%E5%8D%95%E5%90%91%E5%8A%A0%E5%AF%86.md)
   + [JAVA加密系列（二）- 对称加密算法 DES、AES](https://github.com/80945540/encryption/blob/master/src/util/%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86.md)
   + [JAVA加密系列（三）- 非对称加密算法 RSA、DSA](https://github.com/80945540/encryption/blob/master/src/util/%E9%9D%9E%E5%AF%B9%E7%A7%B0%E6%80%A7%E5%8A%A0%E5%AF%86.md)
   + [JAVA加密系列（四）- 位运算加密](https://github.com/80945540/encryption/blob/master/src/util/%E4%BD%8D%E8%BF%90%E7%AE%97%E5%8A%A0%E5%AF%86.md)
<h1>自旋锁</h1>
<pre>
自旋锁采用忙循环，synchronized涉及到线程的用户态和内核态的切换，浪费时间，资源，影响效率。wait，sleep，yield会放弃CPU的控制权，而自旋锁不会。
</pre>

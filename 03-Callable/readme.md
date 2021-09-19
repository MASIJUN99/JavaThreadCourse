# 步骤

1. 实现Callable接口，需要**返回值**类型

2. 重写call方法，需要抛出异常

3. 创建目标对象

4. 创建执行服务`ExecutorService ser = Executors.newFixedThreadPool(1);`

5. 提交执行 `Future<Boolean> result = ser.submit(t1);`

6. 获取结果`boolean r = result.get();`

7. 关闭服务`ser.shutdownNow();`

# 特点

必须有返回值
## 125_Sentinel热点key(下) P125 - 00:29
```java
@GetMapping("/testHotKey")
@SentinelResource(value="testHotKey",blockHandler="deal_testHotKey")
public String testHotKey(@RequestParam(value="p1",required=false) String p1,@RequestParam(value="p2",required=false) String p2){
	return "---testHotKey";
}
public String deal_testHotKey(String p1,String p2,BlockException exception){
	return "---deal_testHotKey,o(T--T)o";
}
```

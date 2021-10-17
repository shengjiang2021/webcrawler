package webcrawler;

public class WebCrawler {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide an url and a level limit");
            System.exit(0);
        }
        String url = args[0];
        if (!url.startsWith("http")) {
            System.out.println("Please provide an valid url");
            System.exit(0);
        }
        String limitStr = args[1];
        char[] arr = limitStr.toCharArray();
        if (arr.length != 1 || !Character.isDigit(arr[0])) {
            System.out.println("Please provide an valid recursion limit within 0-9");
            System.exit(0);
        }

        int limit = Integer.parseInt(limitStr);
        GetLinksUtil.printAllLinks(url, 0, limit);
    }
}

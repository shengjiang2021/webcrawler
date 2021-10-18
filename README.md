# webcrawler
This is a level limited web crawler, The application can do the following:
1. Fetch the HTML document at that URL
2. Log/print the URL visited along with all the URLs on the page
3. Loop back to fetch multiple “levels” of pages, NOT just the first page and its immediate children, u can pass a limit as a arugment to the app.
4. multithread added for getting all the links


to start the app, install java first:
1. git clone respository
2. cd into this repo
3. javac GetLinkUtil.java WebCrawler.java
4. java -cp ../ webcrawler.WebCrawler url to fetch level limit
      for exmaple: java -cp ../ webcrawler.WebCrawler https://www.rescale.com 1

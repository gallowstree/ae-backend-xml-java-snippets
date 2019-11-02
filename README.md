# AgileEngine Test Task

It is built on top of [Jsoup](https://jsoup.org/).

### To run:
```
git clone https://github.com/gallowstree/ae-test-task.git
cd ae-test-task
java -jar bin/ElementFinder.jar samples/sample-0-origin.html samples/sample-1-evil-gemini.html
```

### Sample Output for Files in samples/

#### sample-1-evil-gemini.html
```
java -jar bin/ElementFinder.jar samples/sample-0-origin.html samples/sample-1-evil-gemini.html 
0 [main] INFO com.agileengine.Main  - Looking for elements similar to <a id="make-everything-ok-button" class="btn btn-success" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okDone(); return false;"> Make everything OK </a> 
in file=samples/sample-1-evil-gemini.html
Reporting analysis results...
72 [main] INFO com.agileengine.Main  - Total score:1.0384615384615385 candidate: <a class="btn btn-success" href="#"> <strong>Read All Messages</strong> <i class="fa fa-angle-right"></i> </a>
	criteria: Text (fuzzy match) score: 0.038461538461538464
	criteria: class (fuzzy match) score: 1.0
	criteria: href score: 0.0

80 [main] INFO com.agileengine.Main  - Total score:2.949644435902364 candidate: <a class="btn btn-danger" href="#ok" title="Make-Button" onclick="javascript:window.close(); return false;"> Break everyone's OK </a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 1.0
	criteria: title score: 1.0
	criteria: onclick (fuzzy match) score: 0.4380165289256198

81 [main] INFO com.agileengine.Main  - Total score:4.0 candidate: <a class="btn btn-success" href="#check-and-ok" title="Make-Button" rel="done" onclick="javascript:window.okDone(); return false;"> Make everything OK </a>
	criteria: Text (fuzzy match) score: 1.0
	criteria: rel score: 0.0
	criteria: class (fuzzy match) score: 1.0
	criteria: href score: 0.0
	criteria: title score: 1.0
	criteria: onclick (fuzzy match) score: 1.0

84 [main] INFO com.agileengine.Main  - Total score:0.5116279069767442 candidate: <a href="#" class="btn btn-default btn-block">View All Alerts</a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 0.0

85 [main] INFO com.agileengine.Main  - Highest scoring match -> Score: 4.0 path: #root > html > body[2] > div > div[5] > div[9] > div > div > div[5] > a[3]

```

#### sample-2-container-and-clone.html 

```
 java -jar bin/ElementFinder.jar samples/sample-0-origin.html samples/sample-2-container-and-clone.html 
0 [main] INFO com.agileengine.Main  - Looking for elements similar to <a id="make-everything-ok-button" class="btn btn-success" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okDone(); return false;"> Make everything OK </a> 
in file=samples/sample-2-container-and-clone.html
Reporting analysis results...
72 [main] INFO com.agileengine.Main  - Total score:4.7118969825100905 candidate: <a class="btn test-link-ok" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okComplete(); return false;"> Make everything OK </a>
	criteria: Text (fuzzy match) score: 1.0
	criteria: rel score: 1.0
	criteria: class (fuzzy match) score: 0.23255813953488372
	criteria: href score: 1.0
	criteria: title score: 1.0
	criteria: onclick (fuzzy match) score: 0.4793388429752066

75 [main] INFO com.agileengine.Main  - Total score:0.5116279069767442 candidate: <a href="#" class="btn btn-default btn-block">View All Alerts</a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 0.0

75 [main] INFO com.agileengine.Main  - Total score:2.949644435902364 candidate: <a href="#incorrect-link" class="btn btn-info" style="display:none" title="Make-Button" onclick="javascript:window.close(); return false;"> Make everything OK </a>
	criteria: Text (fuzzy match) score: 1.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 0.0
	criteria: title score: 1.0
	criteria: onclick (fuzzy match) score: 0.4380165289256198

76 [main] INFO com.agileengine.Main  - Highest scoring match -> Score: 4.7118969825100905 path: #root > html > body[2] > div > div[5] > div[9] > div > div > div[5] > div > a

```

#### sample-3-the-escape.html 
```
java -jar bin/ElementFinder.jar samples/sample-0-origin.html samples/sample-3-the-escape.html 
0 [main] INFO com.agileengine.Main  - Looking for elements similar to <a id="make-everything-ok-button" class="btn btn-success" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okDone(); return false;"> Make everything OK </a> 
in file=samples/sample-3-the-escape.html
Reporting analysis results...
70 [main] INFO com.agileengine.Main  - Total score:3.2188752051331333 candidate: <a href="#ok" class="btn btn-warning" rel="next" onclick="javascript:window.close(); return false;"> Make something somehow </a>
	criteria: Text (fuzzy match) score: 0.2692307692307692
	criteria: rel score: 1.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 1.0
	criteria: onclick (fuzzy match) score: 0.4380165289256198

70 [main] INFO com.agileengine.Main  - Total score:4.0 candidate: <a class="btn btn-success" href="#ok" title="Do-Link" rel="next" onclick="javascript:window.okDone(); return false;"> Do anything perfect </a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: rel score: 1.0
	criteria: class (fuzzy match) score: 1.0
	criteria: href score: 1.0
	criteria: title score: 0.0
	criteria: onclick (fuzzy match) score: 1.0

72 [main] INFO com.agileengine.Main  - Total score:0.5116279069767442 candidate: <a href="#" class="btn btn-default btn-block">View All Alerts</a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 0.0

72 [main] INFO com.agileengine.Main  - Highest scoring match -> Score: 4.0 path: #root > html > body[2] > div > div[5] > div[9] > div > div > div[9] > a

```

#### sample-4-the-mash.html

```
java -jar bin/ElementFinder.jar samples/sample-0-origin.html samples/sample-4-the-mash.html 
0 [main] INFO com.agileengine.Main  - Looking for elements similar to <a id="make-everything-ok-button" class="btn btn-success" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okDone(); return false;"> Make everything OK </a> 
in file=samples/sample-4-the-mash.html
Reporting analysis results...
76 [main] INFO com.agileengine.Main  - Total score:4.479338842975206 candidate: <a class="btn btn-success" href="#ok" title="Make-Button" rel="next" onclick="javascript:window.okFinalize(); return false;"> Do all GREAT </a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: rel score: 1.0
	criteria: class (fuzzy match) score: 1.0
	criteria: href score: 1.0
	criteria: title score: 1.0
	criteria: onclick (fuzzy match) score: 0.4793388429752066

79 [main] INFO com.agileengine.Main  - Total score:0.5116279069767442 candidate: <a href="#" class="btn btn-default btn-block">View All Alerts</a>
	criteria: Text (fuzzy match) score: 0.0
	criteria: class (fuzzy match) score: 0.5116279069767442
	criteria: href score: 0.0

80 [main] INFO com.agileengine.Main  - Highest scoring match -> Score: 4.479338842975206 path: #root > html > body[2] > div > div[5] > div[9] > div > div > div[9] > a

```
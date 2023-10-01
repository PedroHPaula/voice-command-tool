# Overview
A simple script in Java to use Linux Terminal commands and other commands,
such as keyboard shortcuts. It was written as an adaptation of [this Python script](https://github.com/PedroHPaula/voice-terminal-command)
while also trying to demonstrate some concepts related to Design Patterns. The classes implemented
through the "Command" interface are an example of Strategy. The classes related to the Keyboard handling
and Julius use the Singleton design.

The script itself works by getting a text output
that represents user's input voice from the microphone of his device
and inserting text or performing the desired action via voice command. The transcription
from voice to text in real time is performed by the [Julius](https://github.com/julius-speech/julius) speech
engine.
# How to use
To run the script you will need (besides the dependencies listed below) to open a Linux Terminal and execution 
permission. The following command can de used to call the script from its root directory:

`java -classpath ./target/classes/ VoiceCommandToolApp`

After that the open terminal window will keep listening to the inputs from the microphone and trying to validate the 
spoken sentences into valid actions. The specific sentence <b>"TERMINAL SILENCE"</b> (or <b>"COMMAND SILENCE"</b>) is 
reserved to be used when you want to exit the script. You could also interrupt it using <b>Ctrl+C</b> in the 
terminal where it was called.


The following table displays the currently
supported terminal commands. The voice command can be either preceded by the 
<b>"TERMINAL"</b> keyword or the <b>"COMMAND"</b> keyword.

| <b>Voice Command</b> | <b>'Terminal Command'</b> |
|:---------------------|:--------------------------|
| ARGUMENT             | 'xargs'                   |
| COPY                 | 'cp'                      |
| CD                   | 'cd'                      |
| CLEAR                | 'clear'                   |
| DIRECTORY            | 'mkdir'                   |
| ECHO                 | 'echo'                    |
| EXIT                 | 'exit'                    |
| FIND                 | 'find'                    |
| GLOBAL               | 'grep'                    |
| LESS                 | 'less'                    |
| LIST                 | 'ls'                      |
| LOCATE               | 'plocate'                 |
| MAN                  | 'man'                     |
| MOVE                 | 'mv'                      |
| NANO                 | 'nano'                    |
| NEW                  | 'gnome-terminal'          |
| REMOVE               | 'rm'                      |
| STREAM               | 'sed'                     |
| SWITCH               | 'sudo'                    |
| TAR                  | 'tar'                     |
| ZIP                  | 'gzip'                    |

The following table displays the supported actions with the CTRL key. They only work when the voice command 
is preceded by the <b>"CONTROL"</b> keyword.

| <b>Voice Command</b> | <b>Control Command</b> |
|:---------------------|:-----------------------|
| CLEAR                | CTRL+L                 |
| CLIP                 | CTRL+X                 |
| COPY                 | CTRL+C                 |
| CUT                  | CTRL+X                 |
| PASTE                | CTRL+V                 |
| SAVE                 | CTRL+S                 |
| SEARCH               | CTRL+F                 |
| SELECT               | CTRL+A                 |
| UNDO                 | CTRL+Z                 |

The following table displays the supported keys that can be pressed. 
The respective voice command is preceded by the <b>"PRESS"</b> keyword.

| <b>Voice Command</b> | <b>Pressed Key</b> |
|:---------------------|:-------------------|
| UP                   | UP ARROW KEY       |
| UPPER                | UP ARROW KEY       |
| RIGHT                | RIGHT ARROW KEY    |
| DOWN                 | DOWN ARROW KEY     |
| LEFT                 | LEFT ARROW KEY     |
| HOME                 | HOME               |
| END                  | END                |
| TAB                  | TAB                |
| TABULAR              | TAB                |
| CAPS                 | CAPS LOCK          |
| CAPS LOCK            | CAPS LOCK          |
| ENTER                | ENTER              |
| SPACE                | SPACE              |

The remaining commands / voice inputs that appear in the scripts / grammar files are either experimental 
or will be implemented later.

# Dependencies
You basically need the following dependencies before running the script:
- A Java interpreter with the version equal or superior to 11 (installation depends on the Linux Distro you are using)
- The Julius binary as well as the related acoustic model files, configuration file and voca and grammar files

The Julius dependencies are all already present in this repo. The binary, the acoustic model files as well as the 
dictionary of all possible recognizable words is the latest provided by the [VoxForge project](https://www.voxforge.org/). 
The "`terminal.voca`" and "`terminal.grammar`" files define the set of all words that we want to recognize and the 
possible sentences in which they can be arranged. The remaining "`terminal.*`" files can be generated by calling the 
Perl script "`mkdfa.pl`" with "terminal" as its argument. Finally, the "`conf.jconf`" file contains all the 
options and high-level configurations applied to improve Julius recognition.

For more info please check [Julius' page](http://julius.osdn.jp/en_index.php) and [VoxForge's page](https://www.voxforge.org/)
# References
"Large Vocabulary Continuous Speech Recognition Engine Julius" , i.e., Julius:
> A. Lee, T. Kawahara and K. Shikano. "Julius --- An Open Source Real-Time Large Vocabulary Recognition Engine".  In Proc. EUROSPEECH, pp.1691--1694, 2001.

> A. Lee and T. Kawahara. "Recent Development of Open-Source Speech Recognition Engine Julius" Asia-Pacific Signal and Information Processing Association Annual Summit and Conference (APSIPA ASC), 2009.

> A. Lee and T. Kawahara: Julius v4.5 (2019) https://doi.org/10.5281/zenodo.2530395

Acoustic models and phonetic dictionary by Voxforge:
> https://www.voxforge.org/home/downloads
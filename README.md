# Feeds-analyzer

This is a CLI news RSS analyzer. Given a link to an XML-formatted news feed, it returns the "named entities" that match the words from a dictionary provided as a database. This tool also generates statistics about the occurrences of named entities by topics and categories.

## Dependencies

Java 17 (JRE and JDK) is required. On Debian-based Linux distributions, you can install it with:

```bash
apt install openjdk-17-jdk openjdk-17-jre
```

## Compile

* To compile the code, this will create all binary files needed in the `./bin` directory.

```bash
make
```

* To removes all previously generated `.class` files.

```bash
make clean
```

## Run

To run the app use:
```bash
make run ARGS="[Options]"
```

Where the options are:

<table>
  <tr>
   <td><strong>Argument</strong>
   </td>
   <td><strong>Description</strong>
   </td>
   <td><strong>Possible values</strong>
   </td>
  </tr>
  <tr>
   <td>-h, --help
   </td>
   <td>Prints a help message
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>-f, --feed
   </td>
   <td>Process a specific feed
   </td>
   <td>p12pais
<p>
p12eco
<p>
lmgral
<p>
lmnoti
   </td>
  </tr>
  <tr>
   <td>-ne, --named-entity
   </td>
   <td>Sets the heuristic for analysis and stats.
   </td>
   <td>per_sentence
   <p>
capitalized_word
<p>
not_after_dot
<p>
unit_capitalized_word
   </td>
  </tr>
  <tr>
   <td>-pf, --print-feed
   </td>
   <td> If present prints the proccessed feed.
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>-sf, --stats-format
   </td>
   <td>If present prints the stats by category or topic.
   </td>
   <td>cat
<p>
topic
   </td>
  </tr>
</table>

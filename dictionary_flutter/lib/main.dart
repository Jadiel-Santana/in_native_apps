import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Dictionary Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key,});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final channel = const MethodChannel('method_channel');
  String? word;
  String? definition;

  @override
  void initState() {
    super.initState();

    channel.setMethodCallHandler((call) async {
      if (call.method == 'word_method') {
        setState(() {
          word = call.arguments['word'];
          definition = call.arguments['definition'];
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Dictionary Flutter'),
        leading: IconButton(
          onPressed: () => SystemNavigator.pop(animated: true),
          icon: const Icon(Icons.arrow_back),
        ),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              word ?? 'Carregando...',
            ),
            Text(
              definition ?? 'Carregando...',
            ),
          ],
        ),
      ),
    );
  }
}

## Usage

このプロジェクトはチャット分析APIを提供します。以下の手順で起動できます。

### 準備
OpenAI の API Keyを利用しますので、あらかじめお持ちの環境変数 `OPENAI_API_KEY` を設定してください。

### 起動方法

#### Linux, Mac
以下のコマンドを実行してください。

```bash
./gradlew bootRun
```

Windows
以下のコマンドを実行してください。

```bash
gradlew.bat bootRun
```

起動後、以下のURLにPOSTリクエストを送信することで、チャット分析APIを利用できます。

http://localhost:8080/api/analysis/chat

リクエストボディは以下の形式である必要があります。

```json
{
    "message": "あなたは誰ですか？"
}
```

このAPIは送信されたメッセージを分析し、結果をJSON形式で返します。

## 参考
### OpenAI
API Reference - OpenAI API
https://platform.openai.com/docs/api-reference


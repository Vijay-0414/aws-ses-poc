import React, { useState } from "react";

function App() {

  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [responseMsg, setResponseMsg] = useState("");

  const sendEmail = async () => {

    try {

      const response = await fetch("http://localhost:8080/email/send", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          to: email,
          subject: "Test Email from Aptster",
          message: message
        })
      });

      const data = await response.text();
      setResponseMsg(data);

    } catch (error) {

      setResponseMsg("Failed to connect to backend server");

    }
  };

  return (

    <div style={{ padding: "40px", fontFamily: "Arial" }}>
      <h2>AWS SES Email Sender</h2>

      <div style={{ marginBottom: "10px" }}>
        <input
          type="email"
          placeholder="Enter recipient email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          style={{ width: "300px", padding: "8px" }}
        />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <textarea
          placeholder="Enter your message"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          rows="5"
          cols="40"
          style={{ padding: "8px" }}
        />
      </div>

      <button onClick={sendEmail} style={{ padding: "10px 20px" }}>
        Send Email
      </button>

      {responseMsg && (
        <p style={{ marginTop: "20px", fontWeight: "bold" }}>
          {responseMsg}
        </p>
      )}

    </div>
  );
}

export default App;
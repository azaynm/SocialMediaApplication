import { useEffect } from "react";
import Post from "./components/Post";
import PostsDisplay from "./components/PostsDisplay";

function App() {
  useEffect(() => {
    console.log("data")
    

  });
  return (
    <PostsDisplay/>
  );
}

export default App;

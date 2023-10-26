import React, { useEffect, useState } from 'react'
import Post from './Post'
import axios from 'axios'
import AddPost from './AddPost'
const profile = require("../images/profile.jpg")
const profile2 = require("../images/profile2.jpg")


const PostsDisplay = () => {

  // set current user
  localStorage.setItem("user", 22);

  const [posts, setPosts] = useState([]);
  
  useEffect(() => {
    console.log("data")
    fetchPostItems();

  }, []);

  

  const fetchPostItems = () => {
    axios
      .get(`http://localhost:8082/api/posts`, {})
      .then(res => {
        const data = res.data
        console.log(data)
        setPosts(data);

      })
      .catch((error) => {
        console.log(error)
      })
  }

  return (
    <div>
      <AddPost fetchPostItems={fetchPostItems}/>
      
      {posts && posts.map((data) => (
        <Post data={data} fetchPostItems={fetchPostItems}/>
      ))}
    </div>
  )
}

export default PostsDisplay
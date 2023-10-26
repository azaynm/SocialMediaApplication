

// import axios from 'axios';
// import React, { useEffect, useState } from 'react'
// const like = require("../images/like.png")


// const Post = ({ data }) => {
//     const [post, setPost] = useState(data);
//     const [comments, setComments] = useState([]);
//     const [commentValue, setCommentValue] = useState("");
//     const [isLiked, setIsLiked] = useState(false);
//     const [id, setId] = useState(0);

//     const handleChange = (event) => {
//         setCommentValue(event.target.value);
//     };

//     const fetchComments = async () => {
//         await axios
//             .get(`http://localhost:8082/api/comment/${post.id}`, {})
//             .then(res => {
//                 const data = res.data
//                 console.log(data)
//                 setComments(data);

//             })
//             .catch((error) => {
//                 console.log(error)
//             })
//     }


//     const addComment = (e) => {
//         const commentData = {
//             postId: post.id,
//             username: "User3",
//             userImage: "profile_image.jpg",
//             added_user: 22,
//             comment: commentValue,
//             time: "232"
//         }
//         axios.post('http://localhost:8082/api/comments', commentData)
//             .then(response => {

//                 fetchComments();
//             });
//     }

//     useEffect(() => {
//         console.log("comment data")
//         fetchComments();

//     }, []);

//     const fetchLikeStatus = async () => {
//         const like = {
//             userId: 3,
//             postId: 23
//         }
//         await axios.post(`http://localhost:8082/api/likes/check`, like)
//             .then(async (data) => {
//                 if (data.data != null) {
//                     setIsLiked(true);
//                     setId(data.data);
//                     console.log("Liked Post")
//                 }
//                 else {
//                     setIsLiked(false);
//                     console.log("Unliked Post")
//                 }
//             })
//     }

//     useEffect(() => {
//         fetchLikeStatus();

//     }, []);


//     const addLike = () => {
//         if(isLiked){
//             console.log("Like removed");
//             const like = {
//                 userId: 24,
//                 postId: 34
//             }
//             axios.delete(`http://localhost:8082/api/likes/${id}`)
//         }
//         else{
//             console.log("Like Added");
//             const like = {
//                 userId: 24,
//                 postId: 34
//             }
//             axios.post(`http://localhost:8082/api/likes`, like)
//                 .then((res) => {
//                     console.log("Successfully Added like");
//                 })
//         }

//     }

//     // const comments = [
//     //     {
//     //         userImage: "A",
//     //         userId: "John",
//     //         comment: "Nice picture"
//     //     },
//     //     {
//     //         userImage: "B",
//     //         userId: "Bravo",
//     //         comment: "Amazing"
//     //     }
//     // ]

//     return (
//         <div>
//             <div className="container p-2" style={{ width: '500px' }}>
//                 <div className="col">
//                     <div className="row-sm">
//                         <div className="col-sm my-3 mx-0">
//                             <div className='row'>
//                                 <div className="col-sm-1 mx-0 px-0">
//                                     <img
//                                         src={"uploads/" + `${post.userImage}`}
//                                         style={{ width: "50px", height: "50px", borderRadius: "40px" }}
//                                     />
//                                 </div>
//                                 <div className="col-sm-11 d-flex align-items-center ">{post.userId}</div>
//                             </div>
//                         </div>
//                         <div className="col-sm my-3 mx-0 px-0">
//                             {post.title}
//                         </div>
//                     </div>
//                     <div className="row-sm">
//                         <img

//                             src={"uploads/" + `${post.postImage}`}
//                             style={{ width: '500px', height: '300px' }}
//                         />
//                     </div>
//                     <div className="row-sm">
//                         <div className='row py-3'>
//                             <div className="col-sm d-flex justify-content-flex-start">

//                                 <div>{post.numOfLikes} Likes</div>

//                             </div>

//                         </div>
//                     </div>
//                     <div className="row-sm">
//                         <div className='row py-3'>
//                             <div className="col-sm d-flex justify-content-start">

//                                 {isLiked ? (
//                                     <button style={{ backgroundColor: '#FFEEFF' }}>
//                                         Liked
//                                     <img
//                                         src={`${like}`}
//                                         style={{ width: "30px", height: "30px", }}
//                                         onClick={addLike}
//                                     />
//                                 </button>
//                                 ) : (
//                                     <button style={{ backgroundColor: '#FFEEFF' }}>
//                                         Not liked
//                                     <img
//                                         src={`${like}`}
//                                         style={{ width: "30px", height: "30px" }}
//                                         onClick={addLike}
//                                     />
//                                 </button>
//                                 )}

//                             </div>

//                             <div className="col-sm d-flex justify-content-end">
//                                 <input type='text' placeholder='Enter your comment' onChange={handleChange} value={commentValue} />
//                             </div>

//                             <div className="col-sm d-flex justify-content-end">
//                                 <button style={{ backgroundColor: '#FFEEFF' }} onClick={addComment}>Comment</button>
//                             </div>
//                         </div>
//                     </div>

//                     <div className="row-sm">
//                         See comments
//                     </div>
//                     {comments && <div className="row-sm">
//                         {comments.map((comment) =>
//                         (
//                             <div className="row">
//                                 <div className='col-sm-1'>
//                                     <img
//                                         src={"uploads/" + `${comment.userImage}`}
//                                         style={{ width: "30px", height: "30px", borderRadius: "20px" }}
//                                     />
//                                 </div>
//                                 <div className='col-sm-3 d-flex justify-content-start align-items-center'>{comment.username}</div>
//                                 <div className='col-sm-8 d-flex justify-content-start align-items-center'>{comment.comment}</div>

//                             </div>
//                         )

//                         )}
//                     </div>}

//                 </div>
//             </div>


//         </div>
//     )
// }

// export default Post


import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Swal from 'sweetalert2';
import Comment from './Comment';

const like = require("../images/like.png")


const Post = ({ data, fetchPostItems }) => {
  const [post, setPost] = useState(data);
  const [comments, setComments] = useState([]);
  const [commentValue, setCommentValue] = useState('');
  const [isLiked, setIsLiked] = useState(false);
  const [likeId, setLikeId] = useState(null);
  const [likes, setLikes] = useState(null);

  const handleChange = (event) => {
    setCommentValue(event.target.value);
  };

  const fetchComments = async () => {
    try {
      const res = await axios.get(`http://localhost:8082/api/comment/${post.id}`);
      setComments(res.data);
    } catch (error) {
      console.log(error);
    }
  };

  const fetchLikes = async () => {
    try {
      const res = await axios.get(`http://localhost:8082/api/like/count/${post.id}`);
      setLikes(res.data);
    } catch (error) {
      console.log(error);
    }

  }

  const deletePost = async (id) => {

    Swal.fire({
      title: 'Are you sure?',
      text: "Are you sure you want to delete this post!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          const res = await axios.delete(`http://localhost:8082/api/posts/${id}`);
          fetchPostItems();
        } catch (error) {
          console.log(error);
        }
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
      }
    })


  }




  const addComment = () => {
    const commentData = {
      postId: post.id,
      username: localStorage.getItem('user'),
      userImage: 'profile_image.jpg',
      added_user: localStorage.getItem('user'),
      comment: commentValue,
      time: '232',
    };
    axios.post('http://localhost:8082/api/comments', commentData)
      .then(() => {
        fetchComments();
        setCommentValue('');
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchLikeStatus = async () => {
    const likeData = {
      userId: 3,
      postId: post.id,
    };
    try {
      const res = await axios.post('http://localhost:8082/api/likes/check', likeData);
      if (res.data) {
        setIsLiked(true);
        setLikeId(res.data);
        console.log(res.data);
        console.log('Liked Post');
      } else {
        setIsLiked(false);
        console.log('Unliked Post');
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchLikeStatus();
  }, [setLikeId]);


  useEffect(() => {
    fetchLikeStatus();
  }, []);


  const addLike = () => {
    if (isLiked) {
      console.log('Like removed');
      axios.delete(`http://localhost:8082/api/likes/${likeId}`)
        .then(() => {
          setIsLiked(false);
          setLikeId(null);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      console.log('Like Added');
      const likeData = {
        userId: 3,
        postId: post.id,
      };
      axios.post('http://localhost:8082/api/likes', likeData)
        .then((res) => {
          setIsLiked(true);
          setLikeId(res.data.id);
          console.log('Successfully Added like');
        })
        .catch((error) => {
          console.log(error);
        });
    }


  };


  useEffect(() => {
    fetchLikes();
  }, [addLike]);

  

  return (

    <div>
      
        <div className="container p-2" style={{ width: '500px' }}>
          
          <div className="col">
            <div className="row-sm">
              <div className="col-sm my-3 mx-0">
                <div className='row'>
                  <div className="col-sm-1 mx-0 px-0">
                    <img
                      src={"uploads/" + `${post.userImage}`}
                      style={{ width: "50px", height: "50px", borderRadius: "40px" }}
                    />
                  </div>
                  <div className="col-sm-11 d-flex align-items-center justify-content-between">
                    <div>{post.userId}</div>
                    <button onClick={() => deletePost(post.id)}>X</button>
                  </div>
                 
                </div>
              </div>
              <div className="col-sm my-3 mx-0 px-0">
                {post.title}
              </div>
            </div>
            <div className="row-sm">
              <img

                src={"uploads/" + `${post.postImage}`}
                style={{ width: '500px', height: '300px' }}
              />
            </div>
            <div className="row-sm">
              <div className='row py-3'>
                <div className="col-sm d-flex justify-content-flex-start">

                  <div>{likes} Likes</div>

                </div>

              </div>
            </div>
            <div className="row-sm">
              <div className='row py-3'>
                <div className="col-sm d-flex justify-content-start">

                  {isLiked ? (

                    <input
                      type='button'
                      src={`${like}`}
                      style={{ width: "80px", height: "30px", background: '#66FF2C' }}
                      onClick={addLike}
                      value="Liked"
                    />

                  ) : (

                    <input
                      type='button'
                      src={`${like}`}
                      style={{ width: "80px", height: "30px" }}
                      onClick={addLike}
                      value="Like"
                    />

                  )}

                </div>

                <div className="col-sm d-flex justify-content-end">
                  <input type='text' placeholder='Enter your comment' onChange={handleChange} value={commentValue} />
                </div>

                <div className="col-sm d-flex justify-content-end">
                  <button style={{ backgroundColor: '#FFEEFF' }} onClick={addComment}>Comment</button>
                </div>
              </div>
            </div>


            {comments && <div className="row-sm">
              {comments.map((comment) =>
              (
                <Comment postId={post.id} comment={comment} fetchComments={fetchComments}/>
              )

              )}
            </div>}

          </div>
        </div>
    </div>
  )
}

export default Post




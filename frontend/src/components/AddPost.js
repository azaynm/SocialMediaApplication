// import axios from 'axios';
// import React, { useState } from 'react'
// import FormData from 'form-data';

// const AddPost = ({ fetchPostItems }) => {

//     const [newPost, setNewPost] = useState(
//         {
//             userImage: 'profile_image.jpg',
//             userId: 'User2',
//             postImage: '',
//             title: '',
//             numOfLikes: 2,
//             time: '3',

//         }
//     );

//     const handleChange = ({ target }) => {
//         setNewPost({ ...newPost, [target.name]: target.value });
//     }

//     const handlePhoto = ({ target }) => {
//         setNewPost({ ...newPost, postImage: target.files[0] });
//         console.log(newPost.postImage);
//     }

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         const postData = new FormData();

//         postData.append('userImage', newPost.userImage);
//         postData.append('userId', newPost.userId);
//         postData.append('postImage', newPost.postImage);
//         postData.append('title', newPost.title);
//         postData.append('numOfLikes', newPost.numOfLikes);
//         postData.append('time', newPost.time);


//         console.log(postData.postImage);

//         await axios.post('http://localhost:8082/api/posts', postData)
//             .then(res => {
//                 console.log(postData);
//                 fetchPostItems();
//             })
//             .catch(err => {
//                 console.log(err);
//             });
//     }

   

//     return (
//         <section class="vh-100">


//             <div class="container-fluid h-custom h-100">
//                 <div class="row d-flex justify-content-center align-items-center h-100s h-100">
//                     <div class="col-md-9 col-lg-6 col-xl-5">

//                         <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
//                             <form onSubmit={handleSubmit} encType="multipart/form-data">

//                                 <div class="form-outline mb-4">
//                                     <input
//                                         type="file"
//                                         name="image"
//                                         onChange={handlePhoto}
//                                     />

//                                 </div>

                            

//                                 <div class="form-outline mb-4">
//                                     <input
//                                         placeholder="Enter Description"
//                                         name="title"
//                                         value={newPost.title}
//                                         onChange={handleChange}
//                                     />
//                                     <label class="form-label" for="form3Example3">Enter post title</label>
//                                 </div>

//                                 <div class="text-center text-lg-start mt-4 pt-2">
//                                     <input type="submit" />
//                                 </div>

//                             </form>
//                         </div>
//                     </div>
//                 </div>
//             </div>

//         </section>
//     )
// }

// export default AddPost


import axios from 'axios';
import React, { useState } from 'react'
import FormData from 'form-data';

const AddPost = ({ fetchPostItems }) => {

    const [newPost, setNewPost] = useState(
        {
            userImage: 'profile_image.jpg',
            userId: 'User2',
            postImage: '',
            title: ''

        }
    );

    const handleChange = ({ target }) => {
        setNewPost({ ...newPost, [target.name]: target.value });
    }

    const handlePhoto = ({ target }) => {
        setNewPost({ ...newPost, postImage: target.files[0] });
        console.log(newPost.postImage);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const postData = new FormData();

        postData.append('userImage', newPost.userImage);
        postData.append('userId', newPost.userId);
        postData.append('postImage', newPost.postImage);
        postData.append('title', newPost.title);


        console.log(postData.postImage);

        await axios.post('http://localhost:8082/api/posts', postData)
            .then(res => {
                console.log(postData);
                fetchPostItems();
            })
            .catch(err => {
                console.log(err);
            });
    }

    return (
        <section class="vh-100">
            <div class="container-fluid h-custom h-100">
                <div class="row d-flex justify-content-center align-items-center h-100s h-100">
                    <div class="col-md-9 col-lg-6 col-xl-5">
                        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                            <form onSubmit={handleSubmit} encType="multipart/form-data">
                                <div class="form-outline mb-4">
                                    <input
                                        type="file"
                                        name="image"
                                        onChange={handlePhoto}
                                    />
                                </div>
                                {newPost.postImage && (
                                    <div class="form-outline mb-4">
                                        <img src={URL.createObjectURL(newPost.postImage)} alt="Uploaded image" width="400" />
                                    </div>
                                )}
                                <div class="form-outline mb-4">
                                    <input
                                        placeholder="Enter Description"
                                        name="title"
                                        value={newPost.title}
                                        onChange={handleChange}
                                    />
                                    <label class="form-label" for="form3Example3">Enter post title</label>
                                </div>
                                <div class="text-center text-lg-start mt-4 pt-2">
                                    <input type="submit" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default AddPost


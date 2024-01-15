<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Admin User Management</title>
</head>
<body>
    <section>
        <div class="container">
            <div class="relative mt-10 mb-6 shadow-lg w-[90%]">
                <p class="font-medium mb-2 inline-block">Admin User Management</p>
                <div class="w-full pb-2 overflow-x-auto">
                    <table class="font-light w-full">
                        <thead class="bg-blue-200">
                            <tr>
                                <th class="w-32 text-start font-bold px-6 py-1 capitalize">
                                   Doctor Name
                                </th>
                                <th class="w-32 text-start font-bold px-6 py-1 capitalize">
                                    Doctor Email
                                </th>
                                <th class="w-32 text-start font-bold px-6 py-1 capitalize">
                                    Specialization
                                </th>
                                <th class="w-32 text-start font-bold px-6 py-1">
                                   Department
                                </th>
                                <th class="w-32 text-start font-bold px-6 py-1">
                                    Action
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="w-32 text-start px-6 py-1">
                                    John Doe
                                </td>

                                <td class="w-48 font-light py-4 rounded-lg flex items-center">
                                    <img
                                        src="<%= request.getContextPath() %>/path/to/profile/image.jpg"
                                        alt="User"
                                        class="w-8 h-8 mr-2 rounded-full"
                                    />
                                    John Doe
                                </td>

                                <td class="w-48 text-start font-light px-6 py-1">
                                    john.doe@example.com
                                </td>
                                <td class="w-48 text-start font-light px-6 py-1">
                                    Admin
                                </td>
                                <td class="w-32 text-start font-light px-6 py-1">
                                    <div class="symbols flex justify-between">
                                        <i class="material-symbols-rounded cursor-pointer text-customBlue-500">
                                            visibility
                                        </i>
                                        <i class="update material-symbols-rounded cursor-pointer text-orange-500">
                                            edit
                                        </i>
                                        <i class="material-symbols-rounded cursor-pointer text-red-500">
                                            delete_forever
                                        </i>
                                        <i class="material-symbols-rounded cursor-pointer">
                                            manage_accounts
                                        </i>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
